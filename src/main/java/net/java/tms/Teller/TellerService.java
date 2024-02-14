package net.java.tms.Teller;

import lombok.extern.slf4j.Slf4j;
import net.java.tms.Utils.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TellerService {
    @Autowired
    TellerRepo tellerRepo;
    public EntityResponse create(Teller teller) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Teller savedTeller = tellerRepo.save(teller);
            savedTeller.setPostedBy("System");
            savedTeller.setPostedTime(LocalDateTime.now());
            savedTeller.setPostedFlag('Y');
            Teller teller1 = tellerRepo.save(savedTeller);
            entityResponse.setMessage("Teller created successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(teller1);

        } catch (Exception e) {
            log.error("Exception {}", e);
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);

        }
        return entityResponse;
    }


    public EntityResponse findById(Long id) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            Optional<Teller> existingTeller = tellerRepo.findById(id);
            if (existingTeller.isPresent()) {
                entityResponse.setMessage("Teller retrieved successfully " + id);
                entityResponse.setStatusCode(HttpStatus.FOUND.value());
                entityResponse.setEntity(existingTeller);

            } else {
                entityResponse.setMessage("Teller Not Found!");
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setEntity(null);
            }

        } catch (Exception e) {
            log.error("An error has occurred while trying to retrieve a teller {}", e);

        }
        return entityResponse;
    }
    public EntityResponse delete(Long id) {
        EntityResponse res = new EntityResponse<>();
        try {
            Optional<Teller> getTeller = tellerRepo.findById(id);
            if (getTeller.isPresent()) {
                Teller teller = getTeller.get();
                tellerRepo.delete(teller);
                res.setMessage("Teller deleted successfully");
                res.setStatusCode(HttpStatus.OK.value());
                res.setEntity(null);
            } else {
                res.setMessage("Teller not found");
                res.setStatusCode(HttpStatus.NOT_FOUND.value());
                res.setEntity(null);
            }

        } catch (Exception e) {
            log.error("Error {}" + e);
        }
        return res;

    }

    public EntityResponse modify(Teller teller) {
        EntityResponse response = new EntityResponse<>();
        try {
            Optional<Teller> existingTeller = tellerRepo.findById(teller.getId());
            if (existingTeller.isPresent()) {
                Teller updatedTeller = existingTeller.get();
                updatedTeller.setName(teller.getName());
                updatedTeller.setEmail(teller.getEmail());
                updatedTeller.setModifiedBy("System");
                updatedTeller.setModifiedTime(LocalDateTime.now());
                updatedTeller.setModifiedFlag('Y');
                //updatedTeller.setNational_id(teller.getNational_id());
                tellerRepo.save(updatedTeller);
                response.setMessage("Teller updated successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(updatedTeller);
            } else {
                response.setMessage("Teller not found !");
                response.setStatusCode(HttpStatus.NO_CONTENT.value());
                response.setEntity(null);
            }
        } catch (Exception e) {
            //   throw new RuntimeException(e);
            log.error("Exception  {}" + e);
        }
        return response;
    }

    public EntityResponse createBulk(List<Teller> tellers) {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            for (Teller teller : tellers) {
                teller.setPostedBy("System");
                teller.setPostedTime(LocalDateTime.now());
                teller.setPostedFlag('Y');
            }

            // To save all tellers in bulk
            List<Teller> savedTellers = tellerRepo.saveAll(tellers);

            entityResponse.setMessage("Tellers created successfully");
            entityResponse.setStatusCode(HttpStatus.CREATED.value());
            entityResponse.setEntity(savedTellers);
        } catch (Exception e) {
            log.error("Exception occurred while creating tellers: {}", e);
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }
    public EntityResponse getAll() {
        EntityResponse entityResponse = new EntityResponse<>();
        try {
            List<Teller> allTellers = tellerRepo.findAll();
            if (!allTellers.isEmpty()) {
                entityResponse.setMessage("All tellers retrieved successfully");
                entityResponse.setStatusCode(HttpStatus.OK.value());
                entityResponse.setEntity(allTellers);
            } else {
                entityResponse.setMessage("No tellers found!");
                entityResponse.setStatusCode(HttpStatus.NO_CONTENT.value());
                entityResponse.setEntity(null);
            }
        } catch (Exception e) {
            log.error("An error has occurred while trying to retrieve all tellers", e);
            entityResponse.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            entityResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            entityResponse.setEntity(null);
        }
        return entityResponse;
    }

}
