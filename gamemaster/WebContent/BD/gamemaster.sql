CREATE DATABASE IF NOT EXISTS gamemaster

CREATE TABLE IF NOT EXISTS console(
id LONG AUTO_INCREMENT PRIMARY KEY, 
titulo VARCHAR(20) NOTNULL,
lancJP DATE NOTNULL,
lanc DATE NOTNULL,
empresa VARCHAR(20) NOTNULL
);

CREATE TABLE IF NOT EXISTS dica (
id LONG AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(50) NOTNULL,
descr VARCHAR(50) NOTNULL,
jogo VARCHAR(50) NOTNULL, 			/*fazer fk depois */
console VARCHAR (50) NOTNULL		/*fazer fk depois */
);

CREATE TABLE IF NOT EXISTS golpe(
id  LONG AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(50) NOTNULL,
personagem VARCHAR(50) NOTNULL,
descr VARCHAR(50) NOTNULL,
jogo VARCHAR(50) NOTNULL, 		/*fazer fk depois */
console VARCHAR(50) NOTNULL,	/*fazer fk depois */
);

CREATE TABLE IF NOT EXISTS jogo(
id LONG AUTO_INCREMENT PRIMARY KEY,
titulo  VARCHAR(50) NOTNULL,
console  VARCHAR(50) NOTNULL,  /*fazer fk depois */
lancJP DATE NOTNULL,
lancUS DATE NOTNULL,
empresa VARCHAR(50) NOTNULL,
imagem VARCHAR(50)
)