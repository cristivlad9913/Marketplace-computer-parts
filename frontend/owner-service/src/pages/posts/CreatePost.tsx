import {
  Button,
  FormControl,
  FormHelperText,
  Input,
  InputLabel,
  TextField,
} from "@material-ui/core";
import { Typography } from "@mui/material";
import axios from "axios";
import { useContext, useState } from "react";
import { UserContext } from "../../App";
import classes from "./styles/Posts.module.css";

const CreatePost = () => {
  const userInfo: any = useContext(UserContext);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  const handleChangeTitle = (e: any) => setTitle(e.target.value);
  const handleChangeDescription = (e: any) => setDescription(e.target.value);

  const createPost = () => {
    const newPost = { title: title, description: description, items: [] };

    axios.post(`http://localhost:8081/posts`, newPost, {
      auth: {
        ...userInfo.user,
      },
    });
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
          id="description"
          label="Description"
          value={description}
          onChange={handleChangeDescription}
        />

        <Button type="button" onClick={createPost}>
          Save
        </Button>
      </FormControl>
    </div>
  );
};

export default CreatePost;
