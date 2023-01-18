import { Button, Paper, Typography } from "@mui/material";
import { Item } from "../Post";
import classes from "../styles/ItemCard.module.css";

type ItemCardProps = {
  item: Item;
  onDelete: (item: Item) => void; 
};


const ItemCard = ({ item, onDelete }: ItemCardProps) => {

  return (
    <Paper className={classes.container} elevation={3}>
      <img
        className={classes.image}
        src="https://barbqvillage.com/wp-content/uploads/woocommerce-placeholder.png"
      />
      <Typography fontSize={20} fontWeight={600}>{item.name}</Typography>
      <Typography fontSize={18}>{item.description}</Typography>
      <Typography fontSize={18}>{item.price} lei</Typography>
      <Button variant="contained" color="error" onClick={() => onDelete(item)}>Delete</Button>
    </Paper>
  );
};

export default ItemCard;
