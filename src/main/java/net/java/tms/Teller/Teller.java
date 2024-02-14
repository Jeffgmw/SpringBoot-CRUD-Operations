package net.java.tms.Teller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Tellers")
public class Teller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "national_id")
    private Long national_id;
    @Column(name = "pfnumber")
    private Long pfnumber;

//Operational Audit
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String postedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime postedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character postedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String modifiedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character modifiedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String deletedBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character deletedFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String createBulkBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createBulkTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character createBulkFlag = 'N';

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String getAllBy;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime getAllTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Character getAllFlag = 'N';
    }

