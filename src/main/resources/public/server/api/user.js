'use strict';

module.exports = app => {
  app.get('/api/user/', (req, res) => {
    res.send([
      {
        "id": 1,
        "firstName": "Nadjim",
        "lastName": "chabane"
      },
      {
        "id": 2,
        "firstName": "Ilyaçe",
        "lastName": "REGAIBI"
      },
      {
        "id": 3,
        "firstName": "Gurnavdeep",
        "lastName": "SINGH"
      },
    ]);
  });
};
