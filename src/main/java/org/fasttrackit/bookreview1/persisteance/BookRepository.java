package org.fasttrackit.bookreview1.persisteance;

import org.fasttrackit.bookreview1.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book,Long> {
 //   Page<Book> findByTitleContaining(String partialTitle, Pageable pageable);

//    Page<Book> findByTitleContainingAndAuthor(
//            String partialTitle, String partialAuthor, Pageable pageable);
//    //JPQL syntax(java persistence query language
//    // @Query("SELECT  product FROM Product  product WHERE name LIKE '%:partialName'")
//    //daca avem nume de tabele le incadram in ` `
//    @Query(value = "SELECT * FROM product WHERE `name` LIKE '%?0%'",nativeQuery = true)
//    Page<Book> findByPartialTitle(String partialTitle,Pageable pageable);

    Page<Book> findByTitleAndAuthor(String partialTitle, String partialAuthor, Pageable pageable);


    @Query(value = "SELECT * FROM  book where `name` Like '%?0%'",
            nativeQuery= true)
    Page<Book> findByTitleOrAuthor(String partialTitle, Pageable pageable);

}
