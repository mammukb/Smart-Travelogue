# Generated by Django 3.2.22 on 2024-02-15 11:52

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('travelogue_app', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='review',
            name='photo2',
        ),
    ]