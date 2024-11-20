-----------------------------TEAMS--------------------------------------------------------
INSERT INTO team (id, team_name)
VALUES
    (1, 'team1'),
    (2, 'team2'),
    (3, 'team3');
-----------------------------PLAYERS--------------------------------------------------------
INSERT INTO player (id, nickname, wins, losses, elo, hours_played, team_id, rating_adjustment)
VALUES
    (1, 'Player1', 0, 0, 0, 0, 1, NULL),
    (2, 'Player2', 0, 0, 0, 0, 1, NULL),
    (3, 'Player3', 0, 0, 0, 0, 1, NULL),
    (4, 'Player4', 0, 0, 0, 0, 1, NULL),
    (5, 'Player5', 0, 0, 0, 0, 1, NULL),

    (6, 'Player6', 0, 0, 0, 0, 2, NULL),
    (7, 'Player7', 0, 0, 0, 0, 2, NULL),
    (8, 'Player8', 0, 0, 0, 0, 2, NULL),
    (9, 'Player9', 0, 0, 0, 0, 2, NULL),
    (10, 'Player10', 0, 0, 0, 0, 2, NULL),

    (11, 'Player11', 0, 0, 0, 0, NULL, NULL),
    (12, 'Player12', 0, 0, 0, 0, NULL, NULL),
    (13, 'Player13', 0, 0, 0, 0, NULL, NULL),
    (14, 'Player14', 0, 0, 0, 0, NULL, NULL),
    (15, 'Player15', 0, 0, 0, 0, NULL, NULL);
