CREATE TABLE book_author (
  book_id VARCHAR(36) NOT NULL,
  author_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (book_id, author_id),
  CONSTRAINT fk_ba_book_id_to_book
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT fk_ba_author_id_to_book
    FOREIGN KEY (author_id) REFERENCES writer (id) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
