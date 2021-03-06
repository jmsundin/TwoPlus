package com.twoplusapp.twoplus.database

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.twoplusapp.twoplus.activities.*
import com.twoplusapp.twoplus.models.UserModel
import com.twoplusapp.twoplus.ui.profile.ProfileFragment
import com.twoplusapp.twoplus.utils.Constants


// A custom class where we will add the operation performed for the firestore database.
class FirestoreClass {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val mFireStore = FirebaseFirestore.getInstance()
    private lateinit var database: FirebaseDatabase

    fun accessDB() {
        // Write a message to the database
        database = Firebase.database
    }

    fun registerUser(activity: SignUpActivity, userInfo: UserModel) {
        mFireStore.collection(Constants.USERS)
            // Document ID for users fields. Here the document it is the User ID.
            .document(getCurrentUserID())
            // Here the userInfo are Field and the SetOption is set to merge. It is for if we want to merge
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                // Here call a function of base activity for transferring the result to it.
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                activity.hideProgressDialog()
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document",
                    e
                )
            }
    }

    /**
     * A function for getting the user id of current logged user.
     */
    fun getCurrentUserID(): String {
        // An Instance of currentUser using FirebaseAuth
        val currentUser = auth.currentUser

        // A variable to assign the currentUserId if it is not null or else it will be blank.
        var currentUserID = ""

        if (currentUser != null) {
            currentUserID = currentUser.uid
        }

        return currentUserID
    }

    fun fetchUserData(activity: Activity?, fragment: Fragment? = null){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(UserModel::class.java)

                when(activity){
                    is SignInActivity -> activity.signInSuccess(loggedInUser)
//                    is MainActivity -> activity.loadUserProfileData(loggedInUser)
                    is SettingsActivity -> activity.loadUserData(loggedInUser)
                }
                when(fragment){
                    is ProfileFragment -> {
                        fragment.loadUserProfile(loggedInUser)
                    }
                }
            }.addOnFailureListener{ e ->
                Log.e(activity?.javaClass?.simpleName, "Error: $e")
            }

    }

    fun signInUser(){

    }

    fun signOutUser(){
        auth.signOut()
    }

    fun deleteAccount(context: Context){
//        Log.d("deleteAccount","Outside let function")
        FirebaseAuth.getInstance().currentUser?.let { it ->
            Log.d("FirebaseUser", it.toString())
            Log.d("FirebaseUserUID", it.uid)
            mFireStore.collection(Constants.USERS).document(it.uid).delete()
            .addOnSuccessListener { it ->
                FirebaseAuth.getInstance().currentUser!!.delete().addOnCompleteListener { it ->
                    Log.d("deleteAccount", it.toString())
                    val intent: Intent = Intent(context, IntroActivity::class.java)
                    context.startActivity(intent)
                }
                    .addOnFailureListener { e ->
                        Log.e("deleteAccountFailure", e.toString())
                    }
            }
        }
    }

//    fun getBoardsList(mainActivity: MainActivity) {
//
//    }
//    /**
//     * A function to SignIn using firebase and get the user details from Firestore Database.
//
//                // Here call a function of base activity for transferring the result to it.
//                when (activity) {
//                    is SignInActivity -> {
//                        activity.signInSuccess(loggedInUser)
//                    }
//                    is MainActivity -> {
//                        activity.updateNavigationUserDetails(loggedInUser, readBoardsList)
//                    }
//                    is MyProfileActivity -> {
//                        activity.setUserDataInUI(loggedInUser)
//                    }
//                }
//            }
//            .addOnFailureListener { e ->
//                // Here call a function of base activity for transferring the result to it.
//                when (activity) {
//                    is SignInActivity -> {
//                        activity.hideProgressDialog()
//                    }
//                    is MainActivity -> {
//                        activity.hideProgressDialog()
//                    }
//                    is ProfileActivity -> {
//                        activity.hideProgressDialog()
//                    }
//                }
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while getting loggedIn user details",
//                    e
//                )
//            }
//    }

    /**
     * A function to update the user profile data into the database.
     */
//    fun updateUserProfileData(activity: Activity, userHashMap: HashMap<String, Any>) {
//        mFireStore.collection(Constants.USERS) // Collection Name
//            .document(getCurrentUserID()) // Document ID
//            .update(userHashMap) // A hashmap of fields which are to be updated.
//            .addOnSuccessListener {
//                Log.e(activity.javaClass.simpleName, "Data updated successfully!")
//
//                // Notify the success result.
//

    /**
     * A function for creating a board and making an entry in the database.
     */
//    fun createBoard(activity: CreateBoardActivity, board: Board) {
//
//        mFireStore.collection(Constants.BOARDS)
//            .document()
//            .set(board, SetOptions.merge())
//            .addOnSuccessListener {
//                Log.e(activity.javaClass.simpleName, "Board created successfully.")
//
//                Toast.makeText(activity, "Board created successfully.", Toast.LENGTH_SHORT).show()
//
//                activity.boardCreatedSuccessfully()
//            }
//            .addOnFailureListener { e ->
//                activity.hideProgressDialog()
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while creating a board.",
//                    e
//                )
//            }
//    }

    /**
     * A function to get the list of created boards from the database.
     */
//    fun getBoardsList(activity: MainActivity) {
//
//        // The collection name for BOARDS
//        mFireStore.collection(Constants.BOARDS)
//            // A where array query as we want the list of the board in which the user is assigned. So here you can pass the current user id.
//            .whereArrayContains(Constants.ASSIGNED_TO, getCurrentUserID())
//            .get() // Will get the documents snapshots.
//            .addOnSuccessListener { document ->
//                // Here we get the list of boards in the form of documents.
//                Log.e(activity.javaClass.simpleName, document.documents.toString())
//                // Here we have created a new instance for Boards ArrayList.
//                val boardsList: ArrayList<Board> = ArrayList()
//
//                // A for loop as per the list of documents to convert them into Boards ArrayList.
//                for (i in document.documents) {
//
//                    val board = i.toObject(Board::class.java)!!
//                    board.documentId = i.id
//
//                    boardsList.add(board)
//                }
//
//                // Here pass the result to the base activity.
//                activity.populateBoardsListToUI(boardsList)
//            }
//            .addOnFailureListener { e ->
//
//                activity.hideProgressDialog()
//                Log.e(activity.javaClass.simpleName, "Error while creating a board.", e)
//            }
//    }

    /**
     * A function to get the Board Details.
     */
//    fun getBoardDetails(activity: TaskListActivity, documentId: String) {
//        mFireStore.collection(Constants.BOARDS)
//            .document(documentId)
//            .get()
//            .addOnSuccessListener { document ->
//                Log.e(activity.javaClass.simpleName, document.toString())
//
//                val board = document.toObject(Board::class.java)!!
//                board.documentId = document.id
//
//                // Send the result of board to the base activity.
//                activity.boardDetails(board)
//            }
//            .addOnFailureListener { e ->
//                activity.hideProgressDialog()
//                Log.e(activity.javaClass.simpleName, "Error while creating a board.", e)
//            }
//    }

    /**
     * A function to create a task list in the board detail.
     */
//    fun addUpdateTaskList(activity: Activity, board: Board) {
//
//        val taskListHashMap = HashMap<String, Any>()
//        taskListHashMap[Constants.TASK_LIST] = board.taskList
//
//        mFireStore.collection(Constants.BOARDS)
//            .document(board.documentId)
//            .update(taskListHashMap)
//            .addOnSuccessListener {
//                Log.e(activity.javaClass.simpleName, "TaskList updated successfully.")
//
//                if (activity is TaskListActivity) {
//                    activity.addUpdateTaskListSuccess()
//                } else if (activity is CardDetailsActivity) {
//                    activity.addUpdateTaskListSuccess()
//                }
//            }
//            .addOnFailureListener { e ->
//                if (activity is TaskListActivity) {
//                    activity.hideProgressDialog()
//                } else if (activity is TaskListActivity) {
//                    activity.hideProgressDialog()
//                }
//                Log.e(activity.javaClass.simpleName, "Error while creating a board.", e)
//            }
//    }

    /**
     * A function to get the list of user details which is assigned to the board.
     */
//    fun getAssignedMembersListDetails(activity: Activity, assignedTo: ArrayList<String>) {
//
//        mFireStore.collection(Constants.USERS) // Collection Name
//            .whereIn(
//                Constants.ID,
//                assignedTo
//            ) // Here the database field name and the id's of the members.
//            .get()
//            .addOnSuccessListener { document ->
//                Log.e(activity.javaClass.simpleName, document.documents.toString())
//
//                val usersList: ArrayList<User> = ArrayList()
//
//                for (i in document.documents) {
//                    // Convert all the document snapshot to the object using the data model class.
//                    val user = i.toObject(User::class.java)!!
//                    usersList.add(user)
//                }
//
//                if (activity is MembersActivity) {
//                    activity.setupMembersList(usersList)
//                } else if (activity is TaskListActivity) {
//                    activity.boardMembersDetailList(usersList)
//                }
//            }
//            .addOnFailureListener { e ->
//                if (activity is MembersActivity) {
//                    activity.hideProgressDialog()
//                } else if (activity is TaskListActivity) {
//                    activity.hideProgressDialog()
//                }
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while creating a board.",
//                    e
//                )
//            }
//    }

    /**
     * A function to get the user details from Firestore Database using the email address.
     */
//    fun getMemberDetails(activity: MembersActivity, email: String) {
//
//        // Here we pass the collection name from which we wants the data.
//        mFireStore.collection(Constants.USERS)
//            // A where array query as we want the list of the board in which the user is assigned. So here you can pass the current user id.
//            .whereEqualTo(Constants.EMAIL, email)
//            .get()
//            .addOnSuccessListener { document ->
//                Log.e(activity.javaClass.simpleName, document.documents.toString())
//
//                if (document.documents.size > 0) {
//                    val user = document.documents[0].toObject(User::class.java)!!
//                    // Here call a function of base activity for transferring the result to it.
//                    activity.memberDetails(user)
//                } else {
//                    activity.hideProgressDialog()
//                    activity.showErrorSnackBar("No such member found.")
//                }
//
//            }
//            .addOnFailureListener { e ->
//                activity.hideProgressDialog()
//                Log.e(
//                    activity.javaClass.simpleName,
//                    "Error while getting user details",
//                    e
//                )
//            }
//    }

    /**
     * A function to assign a updated members list to board.
     */
//    fun assignMemberToBoard(activity: MembersActivity, board: Board, user: User) {
//
//        val assignedToHashMap = HashMap<String, Any>()
//        assignedToHashMap[Constants.ASSIGNED_TO] = board.assignedTo
//
//        mFireStore.collection(Constants.BOARDS)
//            .document(board.documentId)
//            .update(assignedToHashMap)
//            .addOnSuccessListener {
//                Log.e(activity.javaClass.simpleName, "TaskList updated successfully.")
//                activity.memberAssignSuccess(user)
//            }
//            .addOnFailureListener { e ->
//                activity.hideProgressDialog()
//                Log.e(activity.javaClass.simpleName, "Error while creating a board.", e)
//            }
//    }
}
