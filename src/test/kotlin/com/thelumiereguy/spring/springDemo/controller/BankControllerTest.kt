package com.thelumiereguy.spring.springDemo.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.thelumiereguy.spring.springDemo.model.Bank
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper

) {


    @Nested
    @DisplayName("getBanks()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetAllBanks {
        @Test
        fun `should return all banks`() {
            mockMvc.get("/api/banks")
                .andDo {
                    print()
                }.andExpect {
                    status {
                        isOk()
                        content {
                            contentType(MediaType.APPLICATION_JSON)
                        }
                        jsonPath("$[0].accountNumber") {
                            value("12345")
                        }
                    }
                }
        }

    }

    @Nested
    @DisplayName("getBank()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {

        @Test
        fun `should return the bank with the given account number`() {
            val accountNumber = "12345"

            mockMvc.get("/api/bank/$accountNumber").andDo {
                print()
            }.andExpect {
                status {
                    isOk()
                }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    jsonPath("$.trust") {
                        value(3.14)
                    }
                    jsonPath("$.transactionFee") {
                        value(1)
                    }
                }
            }
        }

        @Test
        fun `should return Not Found if the account number does not exist`() {
            val accountNumber = "1"

            mockMvc.get("/api/bank/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }


    @Nested
    @DisplayName("POST /api/bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add the new bank`() {

            val newBank = Bank("45678", 31.0, 2)

            val performPost = mockMvc.post("/api/bank") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            performPost
                .andDo {
                    print()
                }
                .andExpect {
                    status { isCreated() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.accountNumber") {
                            value(newBank.accountNumber)
                        }
                        jsonPath("$.trust") {
                            value(newBank.trust)
                        }
                        jsonPath("$.transactionFee") {
                            value(newBank.transactionFee)
                        }
                    }
                }
        }


        @Test
        fun ` should return bad request if account number already exists`() {
            val invalidBank = Bank("12345", 1.0, 1)


            val performPost = mockMvc.post("/api/bank") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }

            performPost
                .andDo {
                    print()
                }
                .andExpect {
                    status { isBadRequest() }
                }

        }
    }

    @Nested
    @DisplayName("PATCH /api/bank")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {
            val updatedBank = Bank("12345", 3.14, 200)

            val performPatch = mockMvc.patch("/api/bank") {

                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)

            }

            performPatch
                .andDo {
                    print()
                }.andExpect {
                    status { isOk() }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json(objectMapper.writeValueAsString(updatedBank))
                    }
                }


            mockMvc.get("/api/bank/${updatedBank.accountNumber}").andDo {
                print()
            }.andExpect {
                status {
                    isOk()
                }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                    json(objectMapper.writeValueAsString(updatedBank))
                }
            }
        }


        @Test
        fun `should return not found if no bank with account number exists`() {
            val updatedBank = Bank("12346", 3.14, 200)

            val performPatch = mockMvc.patch("/api/bank") {

                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)

            }

            performPatch
                .andDo {
                    print()
                }.andExpect {
                    status { isNotFound() }
                }

        }
    }


    @Nested
    @DisplayName("DELETE /bank/{accountNumber}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteBank {

        @Test
        fun `should delete the bank with the given bank number`() {
            val accountNumber = "12345"

            mockMvc.delete("/api/bank/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("/api/bank/$accountNumber")
                .andDo { print() }
                .andExpect { status { isNotFound() } }
        }
    }

    @Test
    fun `should return not found no bank has the given account number`() {

        val invalidAccountNumber = "abc"

        mockMvc.delete("/api/bank/$invalidAccountNumber")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }
            }

    }
}