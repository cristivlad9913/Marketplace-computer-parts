import { Button, FormControl, TextField } from "@material-ui/core";
import { Typography } from "@mui/material";
import axios from "axios";
import { useContext, useState } from "react";
import { UserContext } from "../../App";
import classes from "./styles/Item.module.css";

// @ts-ignore
function CreateItem({ post, closePage }) {
  const userInfo: any = useContext(UserContext);

  const [title, setTitle] = useState("");
  const [price, setPrice] = useState(0);
  const [description, setDescription] = useState("");

  const handleChangeTitle = (e: any) => setTitle(e.target.value);
  const handleChangePrice = (e: any) => setPrice(e.target.value);
  const handleChangeDescription = (e: any) => setDescription(e.target.value);

  const createItem = async () => {
    const newItem = { title: title, price: price, description: description };
    const updatedPost = post;
    updatedPost.items.push(newItem);

    axios
      .patch(`http://localhost:8081/posts/${post.id}`, updatedPost, {
        auth: {
          ...userInfo.user,
        },
      })
      .then((res) => closePage(res.data));
  };

  return (
    <div className={classes.container}>
      <Typography variant="h4">Create a new post:</Typography>

      <FormControl>
        <TextField
          id="title"
          label="Title"
          value={title}
          onChange={handleChangeTitle}
        />

        <TextField
          id="price"
          label="Price"
          value={price}
          onChange={handleChangePrice}
        />

        <TextField
          id="description"
          label="Description"
          value={description}
          onChange={handleChangeDescription}
        />

        <Button type="button" onClick={createItem}>
          Save
        </Button>
        <Button type="button" onClick={closePage}>
          Cancel
        </Button>
      </FormControl>
    </div>
  );
}

export default CreateItem;
