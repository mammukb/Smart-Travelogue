from django.db import models

# Create your models here.


class Login (models.Model):
    login_id = models.AutoField(primary_key=True)
    username=models.CharField(max_length=200)
    password=models.CharField(max_length=200) 
    utype=models.CharField(max_length=200)
    
class User(models.Model):
    user_id = models.AutoField(primary_key=True)
    login=models.ForeignKey(Login,on_delete=models.CASCADE)
    name=models.CharField(max_length=200)
    place=models.CharField(max_length=200) 
    contact=models.CharField(max_length=200)
    email=models.CharField(max_length=200)
    lati=models.CharField(max_length=200)
    longi=models.CharField(max_length=200)
    photo=models.FileField()
    
    
class Place(models.Model):
    place_id=models.AutoField(primary_key=True)
    place_name=models.CharField(max_length=200)
    city=models.CharField(max_length=200)
    landmark=models.CharField(max_length=200)
    state=models.CharField(max_length=200)
    lati=models.CharField(max_length=200)
    longi=models.CharField(max_length=200)
    status=models.CharField(max_length=200)
    login=models.ForeignKey(Login,on_delete=models.CASCADE)

    
class Notification(models.Model):
    noti_id=models.AutoField(primary_key=True)  
    notification=models.CharField(max_length=200)
    date=models.CharField(max_length=200)
    
class Feedback(models.Model):
    feedback_id=models.AutoField(primary_key=True)  
    feedback=models.CharField(max_length=200)
    date=models.CharField(max_length=200)
    user=models.ForeignKey(User,on_delete=models.CASCADE)

class Review(models.Model):
    review_id=models.AutoField(primary_key=True) 
    place=models.ForeignKey(Place,on_delete=models.CASCADE)
    review=models.CharField(max_length=200)
    rating=models.CharField(max_length=200)
    date=models.CharField(max_length=200)
    photo1=models.FileField()

class Facility(models.Model):
    facility_id=models.AutoField(primary_key=True)  
    type=models.CharField(max_length=200)
    f_name=models.CharField(max_length=200)
    f_place=models.CharField(max_length=200)
    lati=models.CharField(max_length=200)
    longi=models.CharField(max_length=200)
    city=models.CharField(max_length=200)
    state=models.CharField(max_length=200)
    phone=models.CharField(max_length=200)
    email=models.CharField(max_length=200)
    photo=models.FileField()
    
    
class Travelogue(models.Model):
    travelogue_id=models.AutoField(primary_key=True)
    user=models.ForeignKey(User,on_delete=models.CASCADE)
    travel_title=models.CharField(max_length=200)
    description=models.CharField(max_length=200)
    




class Media(models.Model):
    media_id=models.AutoField(primary_key=True)
    place=models.ForeignKey(Place,on_delete=models.CASCADE)
    media_type=models.CharField(max_length=200)
    file=models.FileField()
    title=models.CharField(max_length=200)
    description=models.CharField(max_length=200)
    travelogue=models.ForeignKey(Travelogue,on_delete=models.CASCADE)

    
    
    
class Checkin(models.Model):
    checkin_id=models.AutoField(primary_key=True)
    travelogue=models.ForeignKey(Travelogue,on_delete=models.CASCADE)
    facility=models.ForeignKey(Facility,on_delete=models.CASCADE)
    overall_exp=models.CharField(max_length=200)
    
    
class Package(models.Model):
    package_id=models.AutoField(primary_key=True)
    facility=models.ForeignKey(Facility,on_delete=models.CASCADE)
    package_name=models.CharField(max_length=200)
    description=models.CharField(max_length=200)
    valid_from=models.CharField(max_length=200)
    valid_till=models.CharField(max_length=200)
    price=models.CharField(max_length=200)
    
    
    
    
    
    
    

    
    