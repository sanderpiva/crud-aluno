
CREATE TABLE IF NOT EXISTS supliers(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    telefone VARCHAR(150),
    email VARCHAR(150),
    company_id int(11) NOT NULL
);
