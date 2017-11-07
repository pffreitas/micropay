package br.com.micropay.gateway.api

import br.com.micropay.gateway.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner)
@SpringBootTest(classes = Application)
@AutoConfigureMockMvc
class PaymentMethodListTest {

    @Autowired
    private MockMvc mvc

    @Test
    void testListFraudsterUser() {
        mvc.perform(get("/api/restaurant/1/paymentMethods?userId=1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [
                  {
                    "id": 1,
                    "description": "Cash",
                    "type": "Cash"
                  }, {
                    "id": 2,
                    "description": "Rede POSMachine",
                    "type": "POSMachine"
                  }, {
                    "id": 3,
                    "description": "Cielo POSMachine",
                    "type": "POSMachine"
                  }
                ]
                """))
    }

    @Test
    void testListGoodDinosaur() {
        mvc.perform(get("/api/restaurant/1/paymentMethods?userId=2"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                [
                  {
                    "id": 1,
                    "description": "Cash",
                    "type": "Cash"
                  }, {
                    "id": 2,
                    "description": "Rede POSMachine",
                    "type": "POSMachine"
                  }, {
                    "id": 3,
                    "description": "Cielo POSMachine",
                    "type": "POSMachine"
                  },{
                    "id": 4,
                    "description": "Mastercard",
                    "type": "Online"
                  },
                  {
                    "id": 5,
                    "description": "Visa",
                    "type": "Online"
                  }
                ]
                """))
    }

    @Test
    void testListInvalidRestaurant() {
        mvc.perform(get("/api/restaurant/223/paymentMethods?userId=1"))
                .andExpect(status().isNotFound())
                .andExpect(content().json('{"error": "Restaurant not found"}'))
    }

    @Test
    void testListInvalidUser() {
        mvc.perform(get("/api/restaurant/1/paymentMethods?userId=123"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json('{"error": "Invalid user"}'))
    }
}
