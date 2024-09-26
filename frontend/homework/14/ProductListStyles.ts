
import { createUseStyles } from 'react-jss';

export const useStyles = createUseStyles(() => ({
  body:{
    display:"flex",
    flexWrap:"wrap"
  },
  pageHeading:{
    marginTop:"100rem"
  },
  pageSubHeading:{
    color:"#2a2a72"  
  },
  item:{
    backgroundColor:"#fff",
    display:'inline-block',
    padding:"4rem",
    margin:"1rem",
    height:"20rem",
    width:"12rem"
  },
  itemImage:{
    marginLeft:"1.8rem",
    height:"10rem",
    width:"8rem"
  },
  navbar: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: '#2a2a72',
    padding: '1rem',
    position: 'fixed',
    top: 0,
    width: '100%',
    zIndex: 10,
  },
  navFilterSort:{
    display:"flex",
    marginRight:"5rem"
  },
  navItems:{
    display:'flex',
    marginRight:"3rem",
    color:"#fff",
  },
  navOptions:{
    marginLeft:"1rem",
    backgroundColor:"#fff",
    color:"#000"
  },
  navOptionsHeading:{
    fontSize:"1.5rem"
  },
  navSearchBar:{
    backgroundColor:"#fff",
    color:"#000",
    height:"2rem",
    width:"25rem"
  },
  navSearchButton:{
    backgroundColor:"#fff",
    color:"#000",
    position:"absolute",
    padding:".5rem",
    marginLeft:"0.5rem",
    height:"2.4rem",
    width:"2rem"
  },
  itemTitlePrice:{
    display:'flex',
    justifyContent:"space-around",
    marginTop:"2rem"
  },
  itemTitle:{
    color:"#000"
  },
  itemPrice:{
    color:"#2a2a72",
    fontSize:"1.5rem"
  }
}));
