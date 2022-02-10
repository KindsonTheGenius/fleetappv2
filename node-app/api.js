const express = require('express');
const client = require('./connection.js')
const app = express();

app.listen(3300, ()=>{
    console.log("Sever is now listening at port 3300");
})

app.get('/users', (req, res)=>{
    client.query(`Select * from fleetdb.Country`, (err, result)=>{
        if(!err){
            console.log(result)
            res.send(result);
        } else{
            console.log(err.message);
            res.send(err.message);
        }
    });
    client.end;
})
