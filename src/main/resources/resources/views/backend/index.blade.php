@extends('backend.layouts.app')

@section('title')
    @lang("Dashboard")
@endsection

@section('breadcrumbs')
    <x-backend-breadcrumbs/>
@endsection

@section('content')
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-sm-8">
                    <h4 class="card-title mb-0">@lang("Welcome to", ['name'=>'Ads Operation'])</h4>
                </div>

                <div class="col-sm-4 hidden-sm-down">
                    <div class="btn-toolbar float-right" role="toolbar" aria-label="Toolbar with button groups">
                        <button type="button" class="btn btn-info float-right">
                            <i class="c-icon cil-bullhorn"></i>
                        </button>
                    </div>
                </div>
            </div>
            <hr>
            <livewire:dashboard.index />
        </div>
    </div>
@endsection
@push('after-scripts')
    <script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"></script>
    <script src="https://cdn.plot.ly/plotly-2.16.1.min.js"></script>
@endpush
