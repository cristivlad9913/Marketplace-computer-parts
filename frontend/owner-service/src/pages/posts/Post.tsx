import {
  Typography,
  Button,
  Paper,
  Dialog,
  DialogTitle,
  DialogActions,
  DialogContent,
  TextField,
  Snackbar,
  Alert,
} from "@mui/material";
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import { UserContext } from "../../App";
import CreateItem from "../Items/CreateItem";
import ItemCard from "./components/ItemCard";
import classes from "./styles/Post.module.css";

export type Item = {
  id: number;
  name: string;
  price: number;
  description: string;
};

export type OwnerInfo = {
  id: number;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  phone: string;
};

type PostDetails = {
  id: number;
  title: string;
  description: string;
  total: number;
  status: string;
  items: Item[];
  owner: OwnerInfo;
};

export type PostOffer = {
  offerId: string;
  buyer: OwnerInfo;
  offeredPrice: number;
  status: string;
};

const Post = () => {
  const [postDetails, setPostDetails] = useState<PostDetails | null>();
  const [postOffers, setPostOffers] = useState<PostOffer[] | null>();

  const [makeOfferDialogOpen, setMakeOfferDialogOpen] = useState(false);
  const [successSnackbarOpen, setSuccessSnackbarOpen] = useState(false);
  const [showAddItemPage, setShowAddItemPage] = useState(false);

  const userInfo: any = useContext(UserContext);
  const { id } = useParams();

  const onDeleteItem = (item: Item) => {
    if(postDetails) {setPostDetails({...postDetails, items: postDetails.items.filter(it => it.id !== item.id)})}
  }

  useEffect(() => {
    axios
      .get(`http://localhost:8081/posts/${id}`, {
        auth: {
          ...userInfo.user,
        },
      })
      .then((res) => setPostDetails(res.data))
      .catch((err) => console.error(err));
  }, []);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/posts/${id}/offers`, {
        auth: {
          ...userInfo.user,
        },
      })
      .then((res) => setPostOffers(res.data))
      .catch((err) => console.error(err));
  }, []);

  const handleAddItem = () => setShowAddItemPage(true);

  const closeAddItemPage = (newPostDetails: PostDetails) => {
    setShowAddItemPage(false)
    setPostDetails(newPostDetails);
  };

  const acceptOffer = (offerId: string) => {

    axios.patch(
      `http://localhost:8081/posts/${postDetails?.id}/offers/${offerId}`,
      { status: "ACCEPTED" },
      {auth: {
        ...userInfo.user,
      }}
    ).then(res => {
      if(res.data && postOffers){
      const index = postOffers?.findIndex(offer => offer.offerId === res.data.offerId)
      const copy = [...postOffers]
      copy[index] = res.data
      setPostOffers(copy)
    }});
  };

  const rejectOffer = async (offerId: string) => {

    axios.patch(
      `http://localhost:8081/posts/${postDetails?.id}/offers/${offerId}`,
      { status: "REFUSED" },
      {auth: {
        ...userInfo.user,
      }}
    ).then(res => {
      if(res.data && postOffers){
      const index = postOffers?.findIndex(offer => offer.offerId === res.data.offerId)
      const copy = [...postOffers]
      copy[index] = res.data
      setPostOffers(copy)
    }});
  };

  return (
    <div>
      <div className={classes.container}>
        {postDetails ? (
          <div className={classes.leftContainer}>
            <Paper className={classes.paperContainer}>
              <div
                className={classes.status}
                style={{
                  backgroundColor:
                    postDetails.status === "AVAILABLE"
                      ? "greenyellow"
                      : "salmon",
                  width: postDetails.status === "AVAILABLE" ? "100px" : "120px",
                }}
              >
                <Typography fontWeight={600}>{postDetails.status}</Typography>
              </div>
              <Typography variant="h5">{postDetails.title}</Typography>
              <Typography fontSize={30} fontWeight={700}>
                {postDetails.total} lei
              </Typography>
              <Typography variant="h5">
                DESCRIPTION: {postDetails.description}
              </Typography>
            </Paper>

            <div className={classes.cardsContainer}>
              {postDetails.items.map((item, index) => (
                <ItemCard item={item} key={index} onDelete={onDeleteItem} />
              ))}
              <Button onClick={handleAddItem}>Add item</Button>
            </div>
          </div>
        ) : (
          <Typography variant="h4">No details available</Typography>
        )}

        <div className={classes.rightContainer}>
          {!postOffers || postOffers.length == 0 ? (
            <Typography variant="h4">No offers for this post</Typography>
          ) : (
            <Paper className={classes.ownerContainer}>
              <Typography variant="h4">
                Here are the offers for this post:
              </Typography>
              {/* <div className={classes.postsContainer}> */}
              {postOffers.map((offer) => (
                <div className={classes.infoContainer}>
                  <div className={classes.descriptionContainer}>
                    <Typography fontSize={18}>
                      User: {offer.buyer.username}
                    </Typography>
                    <Typography fontSize={18}>
                      Offered: {offer.offeredPrice} lei
                    </Typography>
                    <Typography fontSize={18}>
                      Status: {offer.status}
                    </Typography>
                  </div>
                  <div className={classes.viewMore}>
                    <Button onClick={() => acceptOffer(offer.offerId)}>ACCEPT</Button>
                  </div>
                  <div className={classes.viewMore}>
                    <Button onClick={() => rejectOffer(offer.offerId)}>REFUSE</Button>
                  </div>
                </div>
              ))}
            </Paper>
          )}
        </div>
      </div>
      <div>
        {showAddItemPage && (
          <CreateItem post={postDetails} closePage={closeAddItemPage} />
        )}
      </div>
    </div>
  );
};

export default Post;
