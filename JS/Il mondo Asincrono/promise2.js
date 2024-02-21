// -Crea una catena di Promises per simulare un'operazione asincrona in più fasi.
// -La prima Promise recupera i dati dell'utente { id: 1, name: 'John' }.
// -La seconda Promise recupera i post dell'utente ['Post 1', 'Post 2', 'Post 3'].
// -Infine, chiama le funzioni per recuperare e stampare in console il nome dell'utente e i titoli dei post.

function fetchUserData() {
  return new Promise((resolve, reject) => {
    const userData = { id: 1, name: "John" };
    const data = true;
    if (data) {
      return resolve(`UserId: ${userData.id} \n UserName: ${userData.name}`);
    } else {
      return reject("Invalid Data");
    }
  });
}

function fetchUserPosts(userId, userName) {
  return new Promise((resolve, reject) => {
    const userPosts = ["Post 1", "Post 2", "Post 3"];
    const data = true;
    if (data) {
      return resolve(
        `Results found: ${userPosts.length} \n UserPosts: ${userPosts}`
      );
    } else {
      return reject("ERROR");
    }
  });
}

fetchUserData()
  .then((userData) => {
    console.log("User Data:", userData);
    return fetchUserPosts(userData.id, userData.name);
  })
  .then((userPosts) => {
    console.log("User Posts:", userPosts);
  })
  .catch((error) => {
    console.error("Error:", error);
  });
