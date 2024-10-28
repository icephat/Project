jQuery(".form-valide").validate({
  ignore: [],
  errorClass: "invalid-feedback animated fadeInDown",
  errorElement: "div",
  errorPlacement: function (e, a) {
    jQuery(a).parents(".form-group > div").append(e);
  },
  highlight: function (e) {
    jQuery(e)
      .closest(".form-group")
      .removeClass("is-invalid")
      .addClass("is-invalid");
  }, 
  success: function (e) {
    jQuery(e).closest(".form-group").removeClass("is-invalid"),
      jQuery(e).remove();
  },
  rules: {
    "val-batch": { required: !0,minlength: 9, maxlength: 9 },
    "val-order": { required: !0, minlength: 12, maxlength: 12 },
    "val-material": { required: !0, minlength: 17, maxlength: 17  },
    "val-detail": { required: !0},
    "val-type": { required: !0 },
    "val-status": { required: !0, minlength: 2 },
    "val-packId": { required: !0, minlength: 14, maxlength: 14 },
    "val-MFG": { required: !0 , date: true},
    "val-EXP": { required: !0 , date: true},
    "val-price": { required: !0,  min: 0.0001},
    "val-inkjet": { required: true },
    "val-line": { required: true },
  },
  messages: {
    "val-batch": {
        required: "Please enter a batch!",
        minlength: "ต้องมี 9 ตัวอักษร",
        maxlength: "ห้ามเกิน 9 ตัวอักษร",
    },
    "val-order": {
        required: "Please enter a order!",
        minlength: "ต้องมี 12 ตัวอักษร",
        maxlength: "ห้ามเกิน 12 ตัวอักษร",
    },
    "val-material": {
        required: "Please enter a material!",
        minlength: "ต้องมี 17 ตัวอักษร",
        maxlength: "ห้ามเกิน 17 ตัวอักษร",
    },
   
    "val-detail": {
      required: "Please enter a detail!",
      
    },
    "val-type": "Please enter a type!",
    "val-status": "Please enter a status!",
    "val-packId": {
        required: "Please enter a pack ID!",
        minlength: "ต้องมี 14 ตัวอักษร",
        maxlength: "ห้ามเกิน 14 ตัวอักษร",
    },
    "val-MFG": {
        required: "Please select MFG date!",
        date: true
    },
    "val-EXP": {
        required: "Please select EXP date!",
        date: true
    },
    "val-price": {
        required: "Please enter price!",
        digits: "ห้ามเป็น 0",
        min : "ห้ามน้อยกว่า หรือเท่ากับ 0"

    },
    "val-inkjet": {
        required: "Please enter inkjet!",

    },
    "val-line": {
        required: "Please enter line!",

    },
  },
});
