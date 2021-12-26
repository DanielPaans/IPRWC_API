package nl.hsleiden.IPRWC.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class LoggedInUser extends User {

}
