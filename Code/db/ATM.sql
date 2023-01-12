-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Gen 12, 2023 alle 16:08
-- Versione del server: 5.7.34
-- Versione PHP: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ATM`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `ATM`
--

CREATE TABLE `ATM` (
  `n_serie` int(10) NOT NULL,
  `n_filiale` int(10) NOT NULL,
  `carta_ok` tinyint(1) NOT NULL,
  `Luogo` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `ATM`
--

INSERT INTO `ATM` (`n_serie`, `n_filiale`, `carta_ok`, `Luogo`) VALUES
(1, 1, 1, 'Bergamo');

-- --------------------------------------------------------

--
-- Struttura della tabella `Conto_Corrente`
--

CREATE TABLE `Conto_Corrente` (
  `n_conto` int(10) NOT NULL,
  `importo_minimo` tinyint(1) NOT NULL,
  `bilancio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Conto_Corrente`
--

INSERT INTO `Conto_Corrente` (`n_conto`, `importo_minimo`, `bilancio`) VALUES
(11111, 1, 5000),
(22222, 0, 10000);

-- --------------------------------------------------------

--
-- Struttura della tabella `Database_Accidenti`
--

CREATE TABLE `Database_Accidenti` (
  `n_carta` int(16) NOT NULL,
  `tipo_accidente` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Database_Accidenti`
--

INSERT INTO `Database_Accidenti` (`n_carta`, `tipo_accidente`) VALUES
(22223333, 'Furto');

-- --------------------------------------------------------

--
-- Struttura della tabella `Ricevuta`
--

CREATE TABLE `Ricevuta` (
  `n_serie` int(10) NOT NULL,
  `n_carta` int(10) NOT NULL,
  `ora_stampa` time NOT NULL,
  `n_atm` int(10) NOT NULL,
  `n_filiale` int(10) NOT NULL,
  `importo_perlievo` double NOT NULL,
  `data_stampa` date NOT NULL,
  `carta_ok` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Tessera_Magnetica`
--

CREATE TABLE `Tessera_Magnetica` (
  `n_carta` int(16) NOT NULL,
  `data_emissione` date NOT NULL,
  `data_scadenza` date NOT NULL,
  `n_conto` int(10) NOT NULL,
  `PIN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Tessera_Magnetica`
--

INSERT INTO `Tessera_Magnetica` (`n_carta`, `data_emissione`, `data_scadenza`, `n_conto`, `PIN`) VALUES
(11112222, '2023-01-12', '2033-01-12', 11111, 11111),
(22223333, '2023-01-04', '2033-01-04', 22222, 22222);

-- --------------------------------------------------------

--
-- Struttura della tabella `Transazione`
--

CREATE TABLE `Transazione` (
  `n_serie` int(10) NOT NULL,
  `tipo_transazione` varchar(20) NOT NULL,
  `ora_transazione` time NOT NULL,
  `n_atm` int(10) NOT NULL,
  `n_filiale` int(10) NOT NULL,
  `importo_prelievo` double NOT NULL,
  `data_transazione` date NOT NULL,
  `importo_minimo` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `Utente`
--

CREATE TABLE `Utente` (
  `n_conto` int(10) NOT NULL,
  `n_carta` int(16) NOT NULL,
  `PIN` int(10) NOT NULL,
  `nome` varchar(20) NOT NULL,
  `cognome` varchar(20) NOT NULL,
  `anno_nascita` date NOT NULL,
  `residenza` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Utente`
--

INSERT INTO `Utente` (`n_conto`, `n_carta`, `PIN`, `nome`, `cognome`, `anno_nascita`, `residenza`) VALUES
(11111, 11112222, 11111, 'Mario', 'Rossi', '2001-01-27', 'Bergamo'),
(22222, 22223333, 22222, 'Matteo ', 'Rossi', '2000-12-30', 'Orio al Serio');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `ATM`
--
ALTER TABLE `ATM`
  ADD PRIMARY KEY (`n_serie`);

--
-- Indici per le tabelle `Conto_Corrente`
--
ALTER TABLE `Conto_Corrente`
  ADD PRIMARY KEY (`n_conto`);

--
-- Indici per le tabelle `Database_Accidenti`
--
ALTER TABLE `Database_Accidenti`
  ADD PRIMARY KEY (`n_carta`);

--
-- Indici per le tabelle `Ricevuta`
--
ALTER TABLE `Ricevuta`
  ADD PRIMARY KEY (`n_serie`);

--
-- Indici per le tabelle `Tessera_Magnetica`
--
ALTER TABLE `Tessera_Magnetica`
  ADD PRIMARY KEY (`n_carta`);

--
-- Indici per le tabelle `Transazione`
--
ALTER TABLE `Transazione`
  ADD PRIMARY KEY (`n_serie`);

--
-- Indici per le tabelle `Utente`
--
ALTER TABLE `Utente`
  ADD PRIMARY KEY (`n_conto`,`n_carta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
