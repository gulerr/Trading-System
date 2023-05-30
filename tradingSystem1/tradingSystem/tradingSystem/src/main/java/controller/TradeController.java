package controller;

import dto.request.OrderRequest;
import dto.request.TradeRequest;
import dto.response.CoinResponse;
import dto.response.OrderResponse;
import dto.response.TradeResponse;
import dto.response.WalletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TradeService;
import java.util.List;

@RestController
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeController {
    private final TradeService tradeService;

    @GetMapping("/customer/{customerId}/coins")
    @ResponseBody
    public ResponseEntity<List<CoinResponse>> getCustomerCoins(@PathVariable Long customerId) {
        List<CoinResponse> coins = tradeService.getCustomerCoins(customerId);
        return ResponseEntity.ok(coins);
    }

    @PostMapping("/customer/{customerId}/newOrder")
    @ResponseBody
    public ResponseEntity<OrderResponse> createOrder(@PathVariable Long customerId, @RequestBody OrderRequest request) {
        OrderResponse order = tradeService.createOrder(customerId, request);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerId}/orders")
    @ResponseBody
    public ResponseEntity<List<OrderResponse>> getCustomerOrders(@PathVariable Long customerId) {
        List<OrderResponse> orders = tradeService.getCustomerOrders(customerId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/order/{orderId}/status")
    @ResponseBody
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        tradeService.updateOrderStatus(orderId, status);
        return ResponseEntity.ok("Order status updated successfully");
    }

    @GetMapping("/customer/{customerId}/wallet")
    @ResponseBody
    public ResponseEntity<List<WalletResponse>> getCustomerWallet(@PathVariable Long customerId) {
        List<WalletResponse> wallet = tradeService.getCustomerWallet(customerId);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/customer/{customerId}/trade")
    public ResponseEntity<TradeResponse> performTrade(@PathVariable Long customerId, @RequestBody TradeRequest request) {
        TradeResponse tradeResponse = tradeService.performTrade(customerId, request);
        return ResponseEntity.ok(tradeResponse);
    }
}
