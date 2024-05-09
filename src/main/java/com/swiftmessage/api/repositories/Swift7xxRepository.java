package com.swiftmessage.api.repositories;

import com.swiftmessage.api.entities.models.Swift7xx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestPart;

import java.util.Optional;

public interface Swift7xxRepository extends JpaRepository<Swift7xx,Long> {

    @Query("SELECT st FROM Swift7xx st WHERE st.referenceAndMac.senderReference =?1 and st.referenceAndMac.transactionReference =?2")
    Optional<Swift7xx> findSwift7xxByReferenceAndMac(@RequestPart String senderReference,
                                                     @RequestPart String transactionReference);
}
