package classwork_jpa_hibernate.model;

import javax.persistence.*;

//@MappedSuperclass
public abstract class IdEntityClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IdEntityClass idEntity = (IdEntityClass) o;

        return id == idEntity.id;

    }

    @Override
    public int hashCode() {
        return id;
    }
}
