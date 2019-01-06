insert into trainer(id, name,lastname, age)
VALUES
  (1, 'Mietek','Kowalski', 60);

insert into player(id, name, lastname, age)
VALUES
  (1, 'Rafal','Dziedzic',27),
  (2, 'Tomek','Kowalski',15),
  (3, 'Kacper','Buttom',19);

insert into training(id, name, time, trainer_id, player_id)
VALUES
   (1, 'siatkówka', 60, 1, 1);

insert into training(id, name, time)
VALUES
   (2, 'koszykówka', 90);

insert into training(id, name, time, trainer_id)
VALUES
   (3, 'siatkówka', 60, 1);