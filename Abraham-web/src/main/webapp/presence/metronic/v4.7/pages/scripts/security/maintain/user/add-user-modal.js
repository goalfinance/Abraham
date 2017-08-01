/**
 * Created by panqingrong on 11/01/2017.
 */
var AddUserForm = function(){
    var initForm = function(){
        var form = $('#form_add_security_user');
        var error = $('.alert-danger', form);

        form.validate({
            errorElement:'span',
            errorClass:'help-block help-block-error',
            focusInvalid:true,
            ignore:'',
            rules:{
                userId:{
                    minlength:2,
                    required:true
                },
                nickName:{
                    minlength:2,
                    required:true
                },
                passwd:{
                    regexPassword:true,
                    required:true
                },
                passwdConfirmed:{
                    required:true,
                    equalTo: "#passwd"
                }
            },
            messages:{
                userId:"用户Id需要至少两位字母",
                nickName:"昵称需要至少两位字母",
                passwdConfirmed:{
                    required:"需要输入确认密码",
                    equalTo:"密码确认需要与登录密码一致"
                }

            },
            invalidHandler: function (event, validator) { //display error alert on form submit
                error.show();
                App.scrollTo(error, -200);
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
                error.hide();
            }
        });
    }

    return {
        init:function(){
            initForm();
        }
    }
}();

if (App.isAngularJsApp() === false) {
    jQuery(document).ready(function() {
        AddUserForm.init();
    });
}
