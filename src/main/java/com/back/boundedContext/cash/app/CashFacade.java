package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.payout.dto.PayoutDto;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashSupport cashSupport;
    private final CashSyncMemberUseCase cashSyncMemberUseCase;
    private final CashCreateWalletUseCase cashCreateWalletUseCase;
    private final CashCompleteOrderPaymentUseCase cashCompleteOrderPaymentUseCase;
    private final CashCompletePayoutUseCase cashCompletePayoutUseCase;

    @Transactional
    public CashMember syncMember(MemberDto member) {
        return cashSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public Wallet createWallet(CashMemberDto holder) {
        return cashCreateWalletUseCase.createWallet(holder);
    }

    @Transactional(readOnly = true)
    public Optional<CashMember> findMemberByUsername(String username) {
        return cashSupport.findMemberByUsername(username);
    }

    @Transactional
    public Optional<Wallet> findWalletByHolder(CashMember holder) {
        return cashSupport.findWalletByHolder(holder);
    }

    @Transactional
    public void completeOrderPayment(OrderDto order, long pgPayAmount) {
        cashCompleteOrderPaymentUseCase.completeOrderPayment(order, pgPayAmount);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolderId(int id) {
        return cashSupport.findWalletByHolderId(id);
    }

    @Transactional
    public void completePayout(PayoutDto payout) {
        cashCompletePayoutUseCase.completePayout(payout);
    }
}
