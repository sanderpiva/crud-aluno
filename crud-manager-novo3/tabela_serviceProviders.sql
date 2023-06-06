CREATE TABLE IF NOT EXISTS serviceProviders(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    telefone VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    company_id INT(11) NOT NULL,
    FOREIGN KEY(company_id)
    REFERENCES companies(id)
);
