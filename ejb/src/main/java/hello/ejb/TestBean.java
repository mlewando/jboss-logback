package hello.ejb;

import javax.ejb.Remote;

@Remote
public interface TestBean {
    Motorcycle getMotorcycle(int id);

    Motorcycle saveMotorcycle(Motorcycle motorcycle);
}
