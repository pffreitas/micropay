package br.com.micropay.gateway.api

import br.com.micropay.business.PaymentMethodListing
import br.com.micropay.business.repository.IRestaurantRepository
import br.com.micropay.business.repository.IUserRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.web.bind.annotation.RequestMethod.GET

@RestController
@RequestMapping("/api/restaurant")
class PaymentMethodList {

    PaymentMethodListing paymentMethodListing

    IUserRepository userRepository

    IRestaurantRepository restaurantRepository

    @Inject
    PaymentMethodList(PaymentMethodListing paymentMethodListing, IUserRepository userRepository, IRestaurantRepository restaurantRepository) {
        this.paymentMethodListing = paymentMethodListing
        this.userRepository = userRepository
        this.restaurantRepository = restaurantRepository
    }

    @RequestMapping(path = "/{restaurantId}/paymentMethods", method = GET)
    final @ResponseBody
    ResponseEntity list(@PathVariable Long restaurantId, @RequestParam Long userId) {

        def restaurant = restaurantRepository.findById(restaurantId)
        if (!restaurant){
            return new ResponseEntity(["error": "Restaurant not found"], NOT_FOUND)
        }

        def user = userRepository.findById(userId)
        if (!user){
            return new ResponseEntity(["error": "Invalid user"], BAD_REQUEST)
        }

        def availablePaymentMethods = paymentMethodListing.list(user, restaurant)
        return new ResponseEntity(availablePaymentMethods, OK)
    }


}
