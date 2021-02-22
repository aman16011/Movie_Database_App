package com.example.movie_database_app.model;

import java.util.List;

class Dates{
    public String maximum;
    public String minimum;
}

public class APIResponse<T>{
    public int page;
    public List<T> results;
    public Dates dates;
    public int total_pages;
    public int total_results;
}
