@extends ('backend.layouts.app')

@section('title') Show Admins @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item route='{{route("backend.admins.index")}}' icon='c-icon cil-people' >
        Admins
    </x-backend-breadcrumb-item>

    <x-backend-breadcrumb-item type="active">Show</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section('content')
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col-8">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> User
                    <small class="text-muted">{{ __('labels.backend.admins.show.action') }} </small>
                </h4>
            </div>
        </div>

        <div class="row mt-4 mb-4">
            <div class="col">
                <div class="table-responsive-sm">
                    <table class="table table-bordered table-hover">

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.name') }}</th>
                            <td>{{ $admin->display_name }}</td>
                        </tr>

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.username') }}</th>
                            <td>{{ $admin->username }}</td>
                        </tr>

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.status') }}</th>
                            <td>{!! $admin->status_label !!}</td>
                        </tr>

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.roles') }}</th>
                            <td>
                                @if($admin->getRoleNames()->count() > 0)
                                    <ul>
                                        @foreach ($admin->getRoleNames() as $role)
                                        <li>{{ ucwords($role) }}</li>
                                        @endforeach
                                    </ul>
                                @endif
                            </td>
                        </tr>

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.created_at') }}</th>
                            <td>{{ $admin->created_at }}<br><small>({{ $admin->created_at}})</small></td>
                        </tr>

                        <tr>
                            <th>{{ __('labels.backend.admins.fields.updated_at') }}</th>
                            <td>{{ $admin->updated_at }}<br/><small>({{ $admin->updated_at }})</small></td>
                        </tr>

                    </table>
                </div>

                <hr>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="form-group">
                    <x-buttons.cancel> {{__('Back')}}</x-buttons.cancel>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
