package org.sid.serviceproduct.Repo;

import org.sid.serviceproduct.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
