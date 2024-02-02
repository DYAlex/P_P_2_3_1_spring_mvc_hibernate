package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    public void removeUser(Long id) {
        entityManager.remove(getUserById(id));
    }
}
