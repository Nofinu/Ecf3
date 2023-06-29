package Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "matches",fetch = FetchType.EAGER)
    private List<User> User;

    @Temporal(TemporalType.DATE)
    private Date dateMatch;

    @OneToOne(mappedBy = "match")
    private Result result;
}
