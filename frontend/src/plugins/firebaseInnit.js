import firebase from 'firebase'
import 'firebase/firestore'
import firebaseCon from './firebaseConfig'

const firebaseApp = firebase.initializeApp(firebaseCon)

export default firebaseApp.firestore()