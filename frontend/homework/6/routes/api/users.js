const express=require("express");
const users=require("../../data/user");

const routes=express.Router();

routes.get("/",(req,res)=>{
    res.json({
        data:users,
    });
});

routes.get("/:id",(req,res)=>{
    const userId = parseInt(req.params.id);
    const user=users.find(user=>user.id==userId);
    if(user){
        res.status(200).json(user);
    }
    else{
        res.status(404).send("user not found");
    }
})
routes.post("/",(req,res)=>{
    const user={
        id:users.length+1,
        name:req.body.name
    }
    users.push(user);
    res.status(201).send("user added successfully");
})

module.exports=routes