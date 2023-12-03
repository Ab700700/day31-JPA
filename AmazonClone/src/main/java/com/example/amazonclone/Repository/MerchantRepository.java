package com.example.amazonclone.Repository;

import com.example.amazonclone.Model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository  extends JpaRepository<Merchant,Integer> {
}
