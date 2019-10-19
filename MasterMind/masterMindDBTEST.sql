DROP DATABASE IF EXISTS mastermindDbTEST;

Create database if not exists masterMindDbTEST; 

use masterMindDbTEST;
CREATE TABLE IF NOT EXISTS  game(
gameId int primary key not null auto_increment,
gameStatus boolean not null,
gameKey int not null

);

CREATE TABLE IF NOT EXISTS  round(
roundId int primary key not null auto_increment,
guess int,
guessTime dateTime not null,
guessResult varChar(25) not null, 
-- guess format "e:0:p:0" where "e" stands for exact matches and "p" 
gameId int
);

ALTER TABLE round
 ADD CONSTRAINT `fkgameRound` FOREIGN KEY (`gameID`) REFERENCES `game`
(`gameID`) ON DELETE NO ACTION;








