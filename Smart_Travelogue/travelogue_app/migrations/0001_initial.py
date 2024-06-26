# Generated by Django 3.2.22 on 2024-02-09 08:52

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Facility',
            fields=[
                ('facility_id', models.AutoField(primary_key=True, serialize=False)),
                ('type', models.CharField(max_length=200)),
                ('f_name', models.CharField(max_length=200)),
                ('f_place', models.CharField(max_length=200)),
                ('lati', models.CharField(max_length=200)),
                ('longi', models.CharField(max_length=200)),
                ('city', models.CharField(max_length=200)),
                ('state', models.CharField(max_length=200)),
                ('phone', models.CharField(max_length=200)),
                ('email', models.CharField(max_length=200)),
                ('photo', models.FileField(upload_to='')),
            ],
        ),
        migrations.CreateModel(
            name='Login',
            fields=[
                ('login_id', models.AutoField(primary_key=True, serialize=False)),
                ('username', models.CharField(max_length=200)),
                ('password', models.CharField(max_length=200)),
                ('utype', models.CharField(max_length=200)),
            ],
        ),
        migrations.CreateModel(
            name='Notification',
            fields=[
                ('noti_id', models.AutoField(primary_key=True, serialize=False)),
                ('notification', models.CharField(max_length=200)),
                ('date', models.CharField(max_length=200)),
            ],
        ),
        migrations.CreateModel(
            name='Place',
            fields=[
                ('place_id', models.AutoField(primary_key=True, serialize=False)),
                ('place_name', models.CharField(max_length=200)),
                ('city', models.CharField(max_length=200)),
                ('landmark', models.CharField(max_length=200)),
                ('state', models.CharField(max_length=200)),
                ('lati', models.CharField(max_length=200)),
                ('longi', models.CharField(max_length=200)),
                ('status', models.CharField(max_length=200)),
                ('login', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.login')),
            ],
        ),
        migrations.CreateModel(
            name='User',
            fields=[
                ('user_id', models.AutoField(primary_key=True, serialize=False)),
                ('name', models.CharField(max_length=200)),
                ('place', models.CharField(max_length=200)),
                ('contact', models.CharField(max_length=200)),
                ('email', models.CharField(max_length=200)),
                ('photo', models.FileField(upload_to='')),
                ('login', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.login')),
            ],
        ),
        migrations.CreateModel(
            name='Travelogue',
            fields=[
                ('travelogue_id', models.AutoField(primary_key=True, serialize=False)),
                ('travel_title', models.CharField(max_length=200)),
                ('description', models.CharField(max_length=200)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.user')),
            ],
        ),
        migrations.CreateModel(
            name='Review',
            fields=[
                ('review_id', models.AutoField(primary_key=True, serialize=False)),
                ('review', models.CharField(max_length=200)),
                ('rating', models.CharField(max_length=200)),
                ('date', models.CharField(max_length=200)),
                ('photo1', models.FileField(upload_to='')),
                ('photo2', models.FileField(upload_to='')),
                ('place', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.place')),
            ],
        ),
        migrations.CreateModel(
            name='Package',
            fields=[
                ('package_id', models.AutoField(primary_key=True, serialize=False)),
                ('package_name', models.CharField(max_length=200)),
                ('description', models.CharField(max_length=200)),
                ('valid_from', models.CharField(max_length=200)),
                ('valid_till', models.CharField(max_length=200)),
                ('price', models.CharField(max_length=200)),
                ('facility', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.facility')),
            ],
        ),
        migrations.CreateModel(
            name='Media',
            fields=[
                ('media_id', models.AutoField(primary_key=True, serialize=False)),
                ('media_type', models.CharField(max_length=200)),
                ('file', models.FileField(upload_to='')),
                ('title', models.CharField(max_length=200)),
                ('description', models.CharField(max_length=200)),
                ('place', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.place')),
                ('travelogue', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.travelogue')),
            ],
        ),
        migrations.CreateModel(
            name='Feedback',
            fields=[
                ('feedback_id', models.AutoField(primary_key=True, serialize=False)),
                ('feedback', models.CharField(max_length=200)),
                ('date', models.CharField(max_length=200)),
                ('user', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.user')),
            ],
        ),
        migrations.CreateModel(
            name='Checkin',
            fields=[
                ('checkin_id', models.AutoField(primary_key=True, serialize=False)),
                ('overall_exp', models.CharField(max_length=200)),
                ('facility', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.facility')),
                ('travelogue', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='travelogue_app.travelogue')),
            ],
        ),
    ]
