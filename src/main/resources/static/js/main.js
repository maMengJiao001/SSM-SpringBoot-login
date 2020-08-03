function getValueById(id) {
    return document.getElementById(id).value;
}

function checkIdContent(contentid, infoid) {
    var text = document.getElementById(contentid);
    var patt = /^[a-zA-Z0-9_-]{3,8}$/;
    var info = document.getElementById(infoid);
    //alert(patt.exec(text.value));
    if (patt.exec(text.value) != null) {
        info.style.display = "none";
        return true;
    } else {
        if (text.value.length == 0) {
            info.style.display = "inline";
            info.innerHTML = "请输入用户名";
            return false;
        } else if (text.value.length < 3 || text.value.length > 8) {
            info.style.display = "inline";
            info.innerHTML = "用户名长度应大于等于3，小于等8";
            return false;
        } else {
            info.style.display = "inline";
            info.innerHTML = "用户名应为3到8位（字母，数字，下划线，减号）";
            return false;
        }
    }
}

function checkPswContent(contentid, infoid) {
    var text = document.getElementById(contentid);
    var patt = /^.*(?=.{3,8}).*(?=.\d).*$/;
    var info = document.getElementById(infoid);
    if (patt.exec(text.value) != null) {
        info.style.display = "none";
        return true;
    } else {
        if (text.value.length == 0) {
            info.style.display = "inline";
            info.innerHTML = "请输入密码";
            return false;
        } else if (text.value.length < 3 || text.value.length > 8) {
            info.style.display = "inline";
            info.innerHTML = "密码长长度应大于等于3，小于等8";
            return false;
        } else {
            info.style.display = "inline";
            info.innerHTML = "密码仅限数字";
            return false;
        }
    }

}

function checkRepeatPswContent(pswid0, pswid1, infoid) {
    var text0 = document.getElementById(pswid0);
    var text1 = document.getElementById(pswid1);
    var info = document.getElementById(infoid);
    if (text0.value != text1.value) {
        info.style.display = "inline";
        info.innerHTML = "两次输入的密码应相同";
        return false;
    } else {
        info.style.display = "none";
        return true;
    }
}

function submitLogin(func0, func1, form) {
    if (func0 && func1) {
        // document.getElementById(form).submit();
        return true;
    } else {
        return false;
    }
}


function submitRegister(func0, func1, func2, form) {
    if (func0 && func1 && func2) {
        // document.getElementById(form).submit();
        return true;
    } else {
        return false;
    }
}

function enableForm(id0, id1) {
    document.getElementById(id1).style.display = "none";
    document.getElementById(id0).style.display = "inline";

}

//ajax查询注册时用户名是否存在
function isExistUser(registerIdText,contentid, infoid) {
    var $text = $("#registerIdText").val;
    var $info = $("#registerIdInfo").val;

    $.post(
        queryServlet,
        "$text=" + $text,
        function (result) {
            if (result == "true") {
                info.style.display = "inline";
                info.innerHTML = "用户名已存在，换一个试试";
                return false;
            } else {
                info.style.display = "none";
                return true;
            }
        }
    );
}