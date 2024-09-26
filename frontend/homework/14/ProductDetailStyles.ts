import { createUseStyles } from 'react-jss';

export const useStyles = createUseStyles(() => ({
    body:{
        height:"1vh"
    },
    productName:{
        marginTop:"3rem",
        marginLeft:"30%",
        color:"#2a2a72",
    },
    mainContainer:{
        display:'flex',
        padding:"5rem"
    },
    productImage:{
        marginRight:"8rem",
        height:"30rem",
        width:"50rem"
    },
    productDetails:{
        display:"flex",
        flexDirection:"column",
        fontSize:"1.2rem"
    },
    productPrice:{
        color:"#2a2a72",
    },
    productRating:{
        marginBottom:"5rem"
    },
    link:{
        border:"2px solid #646cff",
        borderRadius:"1.2rem",
        padding:"1rem"
    }
}));