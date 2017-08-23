/**
 * Created by win10 on 2017/5/12.
 */
// function switchToSy(){
//     window.location.href="home_ysp/homepage_ysp.do";
// }
function switchto(curindex, url){
    for(var i=0;i<3;i++){
        $("#page" + i).removeClass("center2-selected");
    }
    $("#page" + curindex).addClass("center2-selected");
    $("#webContent").attr("src",url);
}

function leftDivScroll(){
    //save last position of scrollTop
    var st_last = 0;
    var sizeFlag = true;
    var w_h = $(window).height();

    window.onscroll=function(){
        var d = window.frames['webContent'].document;
        var oDiv = d.getElementById('zhutileft');
        var rightButton = d.getElementById('rightButtons');
        var st =window.document.body.scrollTop;
        //console.log($(oDiv).height(), $(window).height(),st);
        sizeFlag = $(oDiv).height() >= $(window).height() ? false : true;
        if(sizeFlag ){
            if(((st>60 && st-st_last>0) || (st-st_last<0 && (st-60)>=0))){
                $(oDiv).css("top", (st-60+5)+"px" );
            }else if(st == 0){
                $(oDiv).css("top", "10px");
            }
        }

        if(((st>60 && st-st_last>0) || (st-st_last<0 && (st-60)>=0))){
            $(rightButton).css("top", ((w_h-60)/2-150+st)+"px");
        }else if(st == 0){
            $(rightButton).css("top", ((w_h-60)/2-150)+"px");
        }

        st_last = st;
    }
}
