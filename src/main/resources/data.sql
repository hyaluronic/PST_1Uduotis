INSERT INTO gydytojas (vardas, telNr) VALUES
  ('Aaa', '370111'),
  ('Bbb', '370222'),
  ('Ccc', '370333'),
  ('Ddd', '370444');

INSERT INTO diagnozes (pacientoId, gydytojoId, diagnoze, data) VALUES
	(1, 4, 'Liga1', '01.01'),
	(2, 1, 'Liga2', '01.02'),
	(1, 1, 'Liga3', '01.03'),
	(3, 3, 'Liga4', '01.04'),
	(1, 3, 'Liga1', '01.04');