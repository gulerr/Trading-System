package service;

import dto.request.OrderRequest;
import dto.request.TradeRequest;
import dto.response.CoinResponse;
import dto.response.OrderResponse;
import dto.response.TradeResponse;
import dto.response.WalletResponse;
import entity.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;
import repository.*;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

@Service
@Data
@Builder
public class TradeService {
    private final CoinRepository coinRepository;
    private final OrderRepository orderRepository;
    private final WalletRepository walletRepository;
    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;


    public List<CoinResponse> getCustomerCoins(Long customerId) {
        List<Coin> coins = coinRepository.findByCustomerId(customerId);
        return coins.stream()
                .map(coin -> new CoinResponse(coin.getId(), coin.getName(), coin.getPrice(), coin.getAmount()))
                .collect(Collectors.toList());
    }

    public OrderResponse createOrder(Long customerId, OrderRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Coin coin = coinRepository.findById(request.getCoinId())
                .orElseThrow(() -> new RuntimeException("Coin not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setCoin(coin);
        order.setAmount(request.getAmount());
        order.setStatus("Unfinished");

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(savedOrder.getId(), savedOrder.getType(),
                savedOrder.getAmount(), savedOrder.getPrice(), savedOrder.getStatus());
    }

    public List<OrderResponse> getCustomerOrders(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(order -> new OrderResponse(order.getId(), order.getType(),
                        order.getAmount(), order.getPrice(), order.getStatus()))
                .collect(Collectors.toList());
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);
    }

    public List<WalletResponse> getCustomerWallet(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        List<Wallet> wallets = walletRepository.findByCustomerId(customer.getCustomerid());
        if (wallets.isEmpty()) throw new RuntimeException("Wallet not found");
        List<WalletResponse> walletResponses = new ArrayList<>();
        wallets.forEach(wallet ->
                walletResponses.add(WalletResponse.builder()
                        .walletId(wallet.getId())
                        .balance(wallet.getBalance())
                        .currency(wallet.getCurrency()).build()));
        return walletResponses;
    }

    public TradeResponse performTrade(Long customerId, TradeRequest request) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Coin coin = coinRepository.findById(request.getCoinId())
                .orElseThrow(() -> new RuntimeException("Coin not found"));

        Wallet wallet = customer.getWallets().stream()
                .filter(w -> w.getCoin().equals(coin))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        double purchasePrice = request.getPrice();
        double currentPrice = coin.getPrice();
        double amount = request.getAmount();
        double profit = (currentPrice - purchasePrice) * amount;

        wallet.setAmount(wallet.getAmount() - amount);
        coin.setAmount(coin.getAmount() - amount);
        walletRepository.save(wallet);
        coinRepository.save(coin);

        TradeResponse tradeResponse = new TradeResponse();

        tradeResponse.setTradeId(RandomGenerator.getDefault().nextLong());
        tradeResponse.setStatus("Successfull");
        tradeResponse.setMessage("Have a good day!");
        return tradeResponse;
    }
}
