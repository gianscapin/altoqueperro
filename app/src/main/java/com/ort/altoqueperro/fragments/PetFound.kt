package com.ort.altoqueperro.fragments


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.ort.altoqueperro.R
import com.ort.altoqueperro.viewmodels.PetFoundViewModel
import java.io.IOException
import java.util.*

class PetFound : Fragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = PetFound()
    }

    private lateinit var petTypesSpinner: Spinner

    //lateinit var petPhoto: ImageView
    lateinit var photoUploadButton: Button
    lateinit var photoSendButton: Button
    private lateinit var nextButton: Button
    lateinit var v: View

    private lateinit var rootLayout: ConstraintLayout

    private val viewModel: PetFoundViewModel by activityViewModels()

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.pet_found_fragment, container, false)

        photoUploadButton = v.findViewById(R.id.uploadPictures)
        photoSendButton = v.findViewById(R.id.sendPicture)

        nextButton = v.findViewById(R.id.btnNext)

        rootLayout = v.findViewById(R.id.pet_found_root_layout_1)

        petTypesSpinner = v.findViewById(R.id.pet_types_spinner)
        petTypesSpinner.setSelection(0, false)
        petTypesSpinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            v.context, R.array.pet_types, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            petTypesSpinner.adapter = adapter
        }

        (v.findViewById(R.id.radio_big_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_medium_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_small_pet) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_mini_pet) as RadioButton).setOnClickListener(this)

        (v.findViewById(R.id.radio_male) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_female) as RadioButton).setOnClickListener(this)
        (v.findViewById(R.id.radio_dont_know) as RadioButton).setOnClickListener(this)

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        photoUploadButton.setOnClickListener { launchGallery() }
        photoSendButton.setOnClickListener { uploadImage() }

        return v
    }

    private fun uploadImage() {
        if(filePath != null){
            val ref = storageReference?.child("uploads/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    addUploadRecordToDb(downloadUri.toString())
                } else {
                    // Handle failures
                }
            }?.addOnFailureListener{

            }
        }else{
            Snackbar.make(rootLayout, "Please Upload an Image", Snackbar.LENGTH_SHORT).show()
        }

    }

    private fun addUploadRecordToDb(toString: String) {
        val db = FirebaseFirestore.getInstance()

        val data = HashMap<String, Any>()
        data["imageUrl"] = this

        db.collection("posts")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Snackbar.make(rootLayout, "Saved to DB", Snackbar.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Snackbar.make(rootLayout, "Error saving to DB", Snackbar.LENGTH_SHORT).show()
            }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Seleccionar foto"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if(data == null || data.data == null){
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                uploadImage().setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onClick(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked

            when (view.getId()) {
                R.id.radio_big_pet,
                R.id.radio_medium_pet,
                R.id.radio_small_pet,
                R.id.radio_mini_pet ->
                    if (checked) {
                        viewModel.setPetSize(view.text.toString())
                    }
                R.id.radio_male,
                R.id.radio_female,
                R.id.radio_dont_know ->
                    if (checked) {
                        viewModel.setPetSex(view.text.toString())
                    }
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        viewModel.setPetType(petTypesSpinner.selectedItem.toString())
    }

    override fun onNothingSelected(parent: AdapterView<*>) {}

    override fun onStart() {
        super.onStart()

        nextButton.setOnClickListener {
            if (viewModel.validateStep1()) {
                val action = PetFoundDirections.actionPetFoundToPetFound2()
                v.findNavController().navigate(action)
            } else {
                Snackbar.make(rootLayout, "* Campos obligatorios", Snackbar.LENGTH_SHORT).show()
            }
        }

//        photoUploadButton.setOnClickListener {
//            //do something
//        }
    }

}