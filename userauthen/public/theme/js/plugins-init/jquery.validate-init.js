jQuery(".form-valide").validate({
    rules: {
        "val-batch": {
            required: !0,
            minlength: 9,
            maxlength: 9
        },
        "val-order": {
            required: !0,
            minlength: 12,
            maxlength: 12
        },
        "val-material": {
            required: !0,
            minlength: 5
        },
        "val-detail": {
            required: !0,
            equalTo: "#val-material"
        },
        "val-type": {
            required: !0
        },
        "val-status": {
            required: !0,
            minlength: 2
        },
        "val-packId": {
            required: !0,
            minlength: 5
        },
        "val-inkjet": {
            required: !0
        },
        "val-currency": {
            required: !0,
            currency: ["$", !0]
        },
        "val-website": {
            required: !0,
            url: !0
        },
        "val-phoneus": {
            required: !0,
            phoneUS: !0
        },
        "val-price": {
            required: !0,
            digits: !0
        },
        "val-number": {
            required: !0,
            number: !0
        },
        "val-range": {
            required: !0,
            range: [1, 5]
        },
        "val-terms": {
            required: !0
        }
    }, 
    messages: {
        "val-batch": {
            required: "Please enter a batch",
            minlength: "Your username must consist of at least 3 characters"
        },
        "val-order": "Please enter a valid email address",
        "val-material": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        },
        "val-detail": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long",
            equalTo: "Please enter the same password as above"
        },
        "val-type": "Please select a value!",
        "val-status": "Please select at least 2 values!",
        "val-packId": "What can we do to become better?",
        "val-inkjet": "Please select a skill!",
        "val-currency": "Please enter a price!",
        "val-website": "Please enter your website!",
        "val-phoneus": "Please enter a US phone!",
        "val-price": "Please enter only digits!",
        "val-number": "Please enter a number!",
        "val-range": "Please enter a number between 1 and 5!",
        "val-terms": "You must agree to the service terms!"
    },

    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).parents(".form-group > div").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid"), jQuery(e).remove()
    },
});


jQuery(".form-valide-with-icon").validate({
    rules: {
        "val-batch": {
            required: !0,
            minlength: 3
        },
        "val-material": {
            required: !0,
            minlength: 5
        }
    },
    messages: {
        "val-batch": {
            required: "Please enter a username",
            minlength: "Your username must consist of at least 3 characters"
        },
        "val-material": {
            required: "Please provide a password",
            minlength: "Your password must be at least 5 characters long"
        }
    },

    ignore: [],
    errorClass: "invalid-feedback animated fadeInUp",
    errorElement: "div",
    errorPlacement: function(e, a) {
        jQuery(a).parents(".form-group > div").append(e)
    },
    highlight: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
    },
    success: function(e) {
        jQuery(e).closest(".form-group").removeClass("is-invalid").addClass("is-valid")
    }




});