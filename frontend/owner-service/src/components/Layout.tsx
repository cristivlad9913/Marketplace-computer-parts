import { Route, Routes } from "react-router-dom";
import Post from "../pages/posts/Post";
import Posts from "../pages/posts/Posts";
import Profile from "../pages/profile/Profile";
import Menu from "./Menu";
import classes from "./styles/Layout.module.css";

const Layout = () => {
  return (
    <div className={classes.container}>
      <Menu />
      <Routes>
        <Route path="my-posts" element={<Posts />} />
        <Route path="post/:id" element={<Post />} />
        <Route path="profile" element={<Profile />} />
      </Routes>
    </div>
  );
};

export default Layout;
