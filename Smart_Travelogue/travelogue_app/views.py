import uuid
from django.utils import timezone
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render
from django.core.files.storage import FileSystemStorage
from django.core.serializers.json import DjangoJSONEncoder

from travelogue_app.models import *
# Create your views here.


def home(request):
    places_count = Place.objects.count()  
    dfd = Place.objects.all()
    
    user_c=User.objects.count()
    
    fe=Feedback.objects.count()
    re=Review.objects.count()
    
    fefull=Feedback.objects.all()



    return render(request, "home.html", {'dfd': dfd, 'places_count': places_count,'user_c': user_c,'fe': fe,'re':re,'fefull':fefull})




def log(request):
    if request.method == "POST":
        uname = request.POST.get('uname')
        pwd = request.POST.get('pawd')

        try:
            lg = Login.objects.get(username=uname, password=pwd)
            request.session['login_id']=lg.pk

            if lg.utype == "admin":
               
                return HttpResponse("<script>alert('Login Success !');window.location='adminhome'</script>")
            
            else:
                return HttpResponse("<script>alert('Invalid Entry');window.location='/login'</script>")
        except Login.DoesNotExist:
            return HttpResponse("<script>alert('LOGIN FAILD ');window.location='/login'</script>")

    return render(request,"login.html")


def admin_view_users(request):
    b=User.objects.all()
    return render(request,"admin_view_users.html",{'b':b})


def adminhome(request):
    b=User.objects.all()
    return render(request,"adminhome.html",{'b':b})

def admin_manage_notification(request):
    ss= Notification.objects.all()
    print(ss)
    if request.method == "POST":
        noti = request.POST.get('noti') 
        today_date = timezone.now().date() 
        pop = Notification(notification=noti, date=today_date)
        pop.save()
        return HttpResponse("<script>alert('added');window.location='admin_manage_notification'</script>")

    return render(request, "admin_manage_notification.html",{'ss':ss})


def admin_update_notification(request,id):
    upd=Notification.objects.get(noti_id=id)
    print(upd)
    if request.method=="POST":
        noti = request.POST['noti']
        today_date = timezone.now().date() 
        
        upd.notification=noti
        upd.date=today_date
        upd.save()
        return HttpResponse("<script>alert('Updated');window.location='/admin_manage_notification'</script>")

    return render(request, "admin_manage_notification.html",{'upd':upd})


def admin_delete_notification(request,id):
    dte=Notification.objects.get(noti_id=id)
    dte.delete()
    return HttpResponse("<script>alert('Deleted');window.location='/admin_manage_notification'</script>")

def admin_view_feedback(request):
    feed=Feedback.objects.all()
    return render(request,"admin_view_feedback.html",{'feed':feed})



def admin_manage_places(request):
    ss= Place.objects.all()
    print(ss)
    if request.method == "POST":
        plc_name = request.POST['plc_name']
        city = request.POST['city']
        LandMark = request.POST['lmark']
        State = request.POST['state']
        lati = request.POST['lati']
        longi = request.POST['longi']
        
        pop = Place(place_name=plc_name,city=city,landmark=LandMark,state=State,lati=lati,longi=longi,status="pending",login_id=request.session['login_id'])
        pop.save()
        return HttpResponse("<script>alert('added');window.location='/admin_manage_places'</script>")

    return render(request, "admin_manage_places.html",{'ss':ss})


def admin_update_places(request,id):
    obj=Place.objects.get(place_id=id)
    if 'update' in request.POST:
        plc_name = request.POST['plc_name']
        city = request.POST['city']
        LandMark = request.POST['lmark']
        State = request.POST['state']
        lati = request.POST['lati']
        longi = request.POST['longi']
        
        
        obj.place_name=plc_name
        obj.city=city
        obj.landmark=LandMark
        obj.state=State
        obj.lati=lati
        obj.longi=longi
        obj.save()
        return HttpResponse("<script>alert('updated');window.location='/admin_manage_places'</script>")

    return render(request, "admin_manage_places.html",{'obj':obj})
    
    
def admin_delete_delete(request,id):
    obj=Place.objects.get(place_id=id)
    obj.delete()
    return HttpResponse("<script>alert('Deleted');window.location='/admin_manage_places'</script>")



def admin_manage_package(request):
    ss= Package.objects.all()
    rev=Facility.objects.all()
    
    print(ss)
    if request.method == "POST":
        facility = request.POST['facility']
        pkname = request.POST['pkname']
        description = request.POST['description']
        valid_from = request.POST['valid_from']
        valid_till = request.POST['valid_till']
        price = request.POST['price']
        
        pop = Package(package_name=pkname,description=description,valid_from=valid_from,valid_till=valid_till,price=price,facility_id=facility)
        pop.save()
        return HttpResponse("<script>alert('added');window.location='/admin_manage_package'</script>")

    return render(request,"admin_manage_package.html",{'ss':ss,'rev':rev})




def admin_update_package(request,id):
    rev=Facility.objects.all()
    obj=Package.objects.get(package_id=id)
    if 'update' in request.POST:
        # fa = request.POST.get('fac', None)
        fa = request.POST['fac']
        pkname = request.POST['pkname']
        description = request.POST['description']
        valid_from = request.POST['valid_from']
        valid_till = request.POST['valid_till']
        price = request.POST['price']
        
        
        obj.facility_id=fa
        obj.package_name=pkname
        obj.description=description
        obj.valid_from=valid_from
        obj.valid_till=valid_till
        obj.price=price
        obj.save()
        return HttpResponse("<script>alert('updated');window.location='/admin_manage_package'</script>")

    return render(request,"admin_manage_package.html",{'obj':obj,'rev':rev})
    

def admin_delete_package(request,id):
    obj=Package.objects.get(package_id=id)
    obj.delete()
    return HttpResponse("<script>alert('Deleted');window.location='/admin_manage_package'</script>")

def admin_view_review(request):
    rev=Review.objects.all()
    return render(request,'admin_view_review.html',{'rev':rev})




def admin_manage_facility(request):
    obj=Facility.objects.all()

    if request.method=='POST':
       
        type=request.POST['type']
        name=request.POST['name']
        lati=request.POST['lati']
        longi=request.POST['longi']
        email=request.POST['email']
        place=request.POST['place']
        city=request.POST['city']
        state=request.POST['state']
        phone=request.POST['phone']
        image=request.FILES['img1']
        fs = FileSystemStorage()
        f_nam =fs.save(image.name, image)
        ab=Facility(type=type,f_name=name,f_place=place,lati=lati,longi=longi,city=city,state=state,phone=phone,email=email,photo=f_nam)
        ab.save()
        return HttpResponse("<script>alert('Success');window.location='/admin_manage_facility'</script>")
    return render(request,'admin_manage_facility.html',{'obj':obj})

    


def admin_update_facility(request,id):
    obj1=Facility.objects.get(facility_id=id)
    if 'update' in request.POST:
        type=request.POST['type']
        name=request.POST['name']
        lati=request.POST['lati']
        longi=request.POST['longi']
        email=request.POST['email']
        place=request.POST['place']
        city=request.POST['city']
        state=request.POST['state']
        phone=request.POST['phone']
        image=request.FILES['img1']
        fs = FileSystemStorage()
        f_nam =fs.save(image.name, image)
        
        
        obj1.type=type
        obj1.f_name=name
        obj1.lati=lati
        obj1.longi=longi
        obj1.email=email
        obj1.f_place=place
        obj1.city=city
        obj1.state=state
        obj1.phone=phone
        obj1.photo=f_nam
        obj1.save()
        return HttpResponse("<script>alert('updated');window.location='/admin_manage_facility'</script>")

    return render(request,"admin_manage_facility.html",{'obj1':obj1})
    
    
    

def admin_delete_facility(request,id):
    obj1=Facility.objects.get(facility_id=id)
    obj1.delete()
    return HttpResponse("<script>alert('Deleted');window.location='/admin_manage_facility'</script>")



   
##########################################################################################3





def phonelogin(request):
    uname=request.GET.get('username')
    password=request.GET.get('password')
    
    
    data=[]
    
    try:
        obj=Login.objects.get(username=uname,password=password)
        data.append({
            'login_id':obj.login_id,
            'usertype':obj.utype
        })
        status='success'
        
    except:
        status='error'

    data={
        'status':status,
        'data':data
    }
    
    return JsonResponse(data)







def view_placess(request):
    data=[]
    try:
        dd=Place.objects.all()
        for i in dd:
            data.append({
                'place_id':i.place_id,
                'place_name':i.place_name,
                'city':i.city,
                'landmark':i.landmark,
                'state':i.state,
                'lati':i.lati,
                'longi':i.longi,
                'status':i.status,
            
            })
            if data:
                status="success"
            else:
                status="error"
            
    except:
        status='error'
        
    response = {
        
    'status': status,
        'data': data
    }

    return JsonResponse(response)

    
def send_rating(request):
    print("########################")
    data=[]
    review=request.POST.get("review")
    place_id=request.POST.get('place_id')
    ratingString=request.POST.get('ratingString')
    print("ratingString : ",ratingString)
    photo=request.FILES.get('image')
    fs = FileSystemStorage()
    f_nam =fs.save(photo.name, photo)
        
    
    
    try:
        obj=Review(review=review,rating=ratingString,date=timezone.now().date(),photo1=f_nam,place_id=place_id)
        obj.save()
        if obj:
            data['status']="success"
        else:
            data['status']="failed"
        
    except:
        status = "error"
        
    response = {
        
       'status': status,
        'data': data
    }
    
    return JsonResponse(response)

        

# def send_rating(request):
#     print("########################")
#     data = {}
#     review = request.POST.get("review")
#     place_id = request.POST.get('place_id')
#     photo = request.FILES.get('image')
#     fs = FileSystemStorage()
#     print("mmmmmmmmmmmmmmm",photo)
    
#     print(";;;;;;;;;;;;;;;;;")
#     f_nam = fs.save(photo.name, photo)
#     print("gggggggggggggg")
#     obj = Review(review=review, rating="hh", date='curdate()', photo1=f_nam, place_id=place_id)
#     obj.save()
#     print("////////////////////////")
#     if obj:
#         data['status'] = "success"
#         print("pppppppppppppppppppppp")
#     else:
#         data['status'] = "failed"
#         print(",,,,,,,,,,,,,,,,,,,,,,,,,,")

#     response = {
#         'data': data
#     }
    
#     return JsonResponse(response)

    
    

def view_noti(request):
    data=[]
    try:
        dd=Notification.objects.all()
        for i in dd:
            data.append({
                'noti_id':i.noti_id,
                'noti':i.notification,
                'date':i.date,
               
            
            })
            if data:
                status="success"
            else:
                status="error"
            
    except:
        status='error'
        
    response = {
        
    'status': status,
    'data': data
    }

    return JsonResponse(response)

  



def view_packages(request):
    data=[]
    try:
        dd=Package.objects.all()
        for i in dd:
            data.append({
                'package_id': i.package_id,
                'p_name': i.package_name,
                'details': i.description,
                'valid_from': i.valid_from,
                'valid_till': i.valid_till,
                'price': i.price,
               
            
            })
            if data:
                status="success"
            else:
                status="error"
            
    except:
        status='error'
        
    response = {
        
    'status': status,
    'data': data
    }

    return JsonResponse(response)

from django.db.models import F, FloatField, ExpressionWrapper, Func, Value
from django.db.models.functions import Cos, Sin, Radians

from django.db.models import Func, FloatField

class Acos(Func):
    function = 'ACOS'
    output_field = FloatField()
    
def user_near_by_facility(request):
    data = []
    
    lati = request.GET.get("lati")
    longi = request.GET.get("logi")
    
    print(lati,longi)
    
    if lati is None or longi is None:
        return JsonResponse({'status': 'error', 'message': 'Latitude or longitude not provided in the request.'})
    
    try:
        lati = float(lati)
        longi = float(longi)
        print("##########")
        facilities = Facility.objects.annotate(
            distance=ExpressionWrapper(
                Acos(
                    Cos(Radians(lati)) * Cos(Radians(F('lati'))) *
                    Cos(Radians(F('longi')) - Radians(longi)) +
                    Sin(Radians(lati)) * Sin(Radians(F('lati')))
                ) * Value(3959), 
                output_field=FloatField()
            )
        ).filter(distance__lte=30)  
        print("######",facilities)
        for facility in facilities:
            data.append({
                'facility_id': facility.facility_id,
                'type': facility.type,
                'f_name': facility.f_name,
                'f_place': facility.f_place,
                'city': facility.city,
                'state': facility.state,
                'phone': facility.phone, 
                'email': facility.email,
                'lati': facility.lati,
                'longi': facility.longi,
                
                'photo':str(facility.photo) if facility.photo else None
                
            })
            
        if data:
            status = 'success' 
        else:
            status = 'error' 

    except Exception as e:
        status = 'error'
        data = []
        
    response = {
        'status': status,
        'data': data
    }

    return JsonResponse(response, encoder=DjangoJSONEncoder)





   
def user_view_near_by_places(request):
    data = []
    
    lati = request.GET.get("lati")
    longi = request.GET.get("logi")
    
    print(lati,longi)
    
    if lati is None or longi is None:
        return JsonResponse({'status': 'error', 'message': 'Latitude or longitude not provided in the request.'})
    
    try:
        lati = float(lati)
        longi = float(longi)
        print("##########")
        obj = Place.objects.annotate(
            distance=ExpressionWrapper(
                Acos(
                    Cos(Radians(lati)) * Cos(Radians(F('lati'))) *
                    Cos(Radians(F('longi')) - Radians(longi)) +
                    Sin(Radians(lati)) * Sin(Radians(F('lati')))
                ) * Value(3959), 
                output_field=FloatField()
            )
        ).filter(distance__lte=30)  
        print("######",obj)
        for i in obj:
            data.append({
                'place_id': i.place_id,
                'p_name': i.place_name,
                'city': i.city,
                'state': i.state,
                'lmark': i.landmark, 
                'latitude': i.lati,
                'longitude': i.longi,
                
                
                
            })
            
        if data:
            status = 'success' 
        else:
            status = 'error' 

    except Exception as e:
        status = 'error'
        data = []
        
    response = {
        'status': status,
        'data': data
    }

    return JsonResponse(response)




def multi_file_upload(request):
    if request.method == 'POST':
        login_id = request.POST.get('log_id')
        print("login_id : ", login_id)
        
        file = request.FILES.get('file')
        path = "static/" + str(uuid.uuid4()) + file.name
        with open(path, 'wb+') as destination:
            for chunk in file.chunks():
                destination.write(chunk)
        
        result_list = file.name.split('.')
        print(result_list[1])
        
        c_title = request.POST.get('title')
        user_post = Media(user_id=login_id, title=c_title, file_path=path, file_extension=result_list[1])
        user_post.save()
        
        data = {
            'status': 'success',
            'method': 'Multi_file_upload'
        }
        
        return JsonResponse(data)
    else:
        return render(request, 'upload_form.html')
    
def user_reg(request):
    data={}
    
    fname=request.POST["name"]
    place=request.POST["place"]
    phone=request.POST["phone"]
    email=request.POST["email"]
    photo=request.FILES.get("image")
    uname=request.POST["uname"]
    password=request.POST["password"]
    lati=request.POST['lati']
    logi=request.POST['logi']
    
    
    obj=Login(username=uname,password=password,utype="user")
    obj.save()

    reg = User(name=fname ,place=place, contact=phone, email=email,photo=photo,login_id=obj.login_id,lati=lati,longi=logi)
    reg.save()
    
    if reg:
        data['status']="success"
        
    else:
        data['status']="error"
           
    return JsonResponse(data)



    

def add_travelogue(request):
    data={}
    
    title=request.GET.get("title")
    desc=request.GET.get("desc")
    
    l_id=request.GET.get("login_id")
    
    obj=User.objects.get(login_id=l_id)
    u_id=obj.pk
    
    kk = Travelogue(travel_title=title,description=desc,user_id=u_id)
    kk.save()
    
    if kk:
        data['status']="success"
        
    else:
        data['status']="error"
           
    return JsonResponse(data)
  
    

        


def View_travelogue(request):
    data=[]
    lid=request.GET.get("login")
    obj=User.objects.get(login_id=lid)
    uid=obj.pk
    try:
        
        dd=Travelogue.objects.filter(user_id=uid)
        for i in dd:
            data.append({
                'id':i.travelogue_id,
                'Title':i.travel_title,
                'description':i.description,
            })
            if data:
                status="success"
            else:
                status="error"
            
    except:
        status='error'
        
    response = {       
    'status': status,
    'data': data
    }

    return JsonResponse(response)

  
def user_view_facility(request):
    data = []
    try:
              
        facilities = Facility.objects.all()
        print("######",facilities)
        for facility in facilities:
            data.append({
                'facility_id': facility.facility_id,
                'type': facility.type,
                'f_name': facility.f_name,
                'f_place': facility.f_place,
                'city': facility.city,
                'state': facility.state,
                'phone': facility.phone, 
                'email': facility.email,
                'lati': facility.lati,
                'longi': facility.longi,
                
                'photo':str(facility.photo) if facility.photo else None
                
            })
            
        if data:
            status = 'success' 
        else:
            status = 'error' 

    except Exception as e:
        status = 'error'
        
        
    response = {
        'status': status,
        'data': data
    }

    return JsonResponse(response, encoder=DjangoJSONEncoder)



def Multi_file_upload(request):
    data=[]
    
    title=request.POST.get('title')
    plc_id=request.POST.get('plc_id')
    user_id=request.POST.get('log_id')
    desc=request.POST.get('desc')
    
    

    fileType=request.POST.get('fileType')
    photo=request.FILES.get('file')
    
    fs = FileSystemStorage()
    f_nam =fs.save(photo.name, photo)
        
 
    obj=Media(media_type=fileType,file=f_nam,title=title,description=desc,place_id=plc_id,travelogue_id=user_id)
    obj.save()
    
   
    if obj:
        status = "success"
    else:
        status = "error"
    
    response = {
        
       'status': status,
        'data': data,
        'method':"Multi_file_upload"
       
    }
    
    
    return JsonResponse(response)




def dropdwonviewplace(request):
    data=[]
    try:
        dd=Place.objects.all()
        for i in dd:
            data.append({
                'place_id':i.place_id,
                'place_name':i.place_name,
                
            
            })
            if data:
                status="success"
            else:
                status="error"
            
    except:
        status='error'
        
    response = {
        
    'status': status,
        'data': data,
        'method':"dropdwonviewplace"
    }

    return JsonResponse(response)


# def View_other_travelogue(request):
#     data=[]
#     lid=request.GET.get("login")
#     obj=User.objects.all().exclude(login_id=lid)
#     # uid=obj.pk
#     for ik in obj:
#         try:
            
#             dd=Travelogue.objects.filter(user_id=ik.user_id)
#             for i in dd:
#                 data.append({
#                     'id':i.travelogue_id,
#                     'Title':i.travel_title,
#                     'description':i.description,
                
                
#                 })
#                 if data:
#                     status="success"
#                 else:
#                     status="error"
                
#         except:
#             status='error'
            
#         response = {
            
#         'status': status,
#         'data': data
#         }

#         return JsonResponse(response)


from django.http import JsonResponse
from .models import User, Travelogue

def View_other_travelogue(request):
    data = []
    lid = request.GET.get("login")
    obj = User.objects.all().exclude(login_id=lid)
    
    status = "error"  # Initialize status variable
    
    for ik in obj:
        try:
            dd = Travelogue.objects.filter(user_id=ik.user_id)
            for i in dd:
                data.append({
                    'id': i.travelogue_id,
                    'Title': i.travel_title,
                    'description': i.description,
                })
            
            if data:
                status = "success"
                
        except Exception as e:
            print(f"An error occurred: {e}")
            status = "error"
    
    response = {
        'status': status,
        'data': data
    }
    
    return JsonResponse(response)






def update_pass_location(request):
    latti=request.POST['latti']
    longi=request.POST['longi']
    log_id=request.POST['log_id']
    # print(latti,"=========================================")
    User.objects.filter(login=log_id).update(lati=latti,longi=longi)
    return JsonResponse({'status':'ok'})
def user_add_place(request):
    latti=request.POST['latti']
    longi=request.POST['longi']
    log_id=request.POST['log_id']
    place=request.POST['place']
    city=request.POST['city']
    land=request.POST['land']
    state=request.POST['state']
    print(latti,"=========================================")
    
    obj=Place()
    obj.place_name=place
    obj.city=city
    obj.landmark=land
    obj.state=state
    obj.lati=latti
    obj.longi=longi
    obj.status='pending'
    obj.login_id=log_id
    obj.save()
    return JsonResponse({'status':'ok'})

def view_media(request):
    lid=request.POST['lid']
    print(lid,"==================================")
    print(lid,"==================================")
    print(lid,"==================================")
    obj=Media.objects.filter(travelogue__user__login=lid)
    
    data=[]
    for i in obj:
        print(i.media_id,"++++++++++++++++++++++++++++")
        print(i.media_id,"++++++++++++++++++++++++++++")
        data.append({
                'id':i.media_id,
                'tit':i.title,
                'desc':i.description,
                'pname':i.place.place_name,
                'file':str(i.file),
                'ftype':i.media_type
                
            })
    print(data)
    return JsonResponse({'status':'ok','users':data})

def and_send_feedback(request):
    lid=request.POST['lid']
    fee=request.POST['fee']
    import  datetime
    obj=Feedback()
    obj.feedback=fee
    obj.date=datetime.datetime.now().strftime("%Y-%m-%d")
    obj.user=User.objects.get(login=lid)
    obj.save()
    return JsonResponse({'status':'ok'})
