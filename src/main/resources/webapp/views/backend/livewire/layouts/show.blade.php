@extends('backend.layouts.app')

@section('title') Show {{ucfirst($moduleName)}} @endsection

@section('breadcrumbs')
    <x-backend-breadcrumbs>
        <x-backend-breadcrumb-item route='{{route("backend.{$moduleName}.index")}}' icon='fas fa-cogs'>
            {{ convertSnakeToTitle($moduleName) }}
        </x-backend-breadcrumb-item>

        <x-backend-breadcrumb-item type="active">Show</x-backend-breadcrumb-item>
    </x-backend-breadcrumbs>
@endsection

@section('content')
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col">
                    <h4 class="card-title mb-0">
                        <i class="fas fa-cogs"></i> {{ convertSnakeToTitle($moduleName) }}
                        <small class="text-muted"> Show</small>
                    </h4>
                </div>
{{--                <div class="col-4">--}}
{{--                    <div class="float-right">--}}
{{--                        <a href="{{ route("backend.{$moduleName}.index") }}" class="btn btn-info" ><i class="fas fa-list"></i> List</a>--}}
{{--                    </div>--}}
{{--                </div>--}}
            </div>
            <hr>
            {{$slot}}
        </div>
    </div>
@endsection
