var AddResourceGroupForm = function(){
    var form = $('#form_add_resource_group');
    var errorDiv = $('.alert-danger', form);

    var initFormValidation = function(){

        form.validate({
            errorElement:'span',
            errorClass:'help-block help-block-error',
            focusInvalid:true,
            ignore:'',
            rules:{
                name:{
                    required:true,
                    minlength:2
                }
            },
            messages:{
                name:"资源名需要至少两位字母"

            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                errorDiv.show();
                App.scrollTo(errorDiv, -200);
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function (element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                label
                    .closest('.form-group').removeClass('has-error');  // set success class to the control group
            },

            submitHandler: function (form) {
                errorDiv.hide();
            }
        });
    }

    var initFormFocus = function(){
        $("[autofocus]:first", form).focus();
    }

    return {
        init:function(){
            initFormValidation();
            initFormFocus();
        }
    }
}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        $(document).on('shown.bs.modal', function(){
            window.setTimeout(function(){
                AddResourceGroupForm.init();
            }.bind(this), 100);

        });
    });
}