@extends('backend.layouts.app')

@section('title') List Roles @endsection

@section('breadcrumbs')
<x-backend-breadcrumbs>
    <x-backend-breadcrumb-item type="active" icon='c-icon cil-people'>Roles</x-backend-breadcrumb-item>
</x-backend-breadcrumbs>
@endsection

@section('content')
<div class="card">
    <div class="card-body">
        <div class="row">
            <div class="col">
                <h4 class="card-title mb-0">
                    <i class="c-icon cil-people"></i> Roles <small class="text-muted">List</small>
                </h4>
            </div>

            <div class="col-4">
                <div class="float-right">
                    <x-buttons.create route='{{ route("backend.roles.create") }}' title="{{__('Create')}}">
                        {{__('Create')}}
                    </x-buttons.create>
                </div>
            </div>
        </div>

        <div class="form-horizontal content-loading mt-4">
            <x-loading/>
            <table class="table table-bordered table-hover table-responsive-sm">
                    <thead>
                        <tr>
                            <th>{{ __("labels.backend.roles.fields.name") }}</th>
                            <th>{{ __("labels.backend.roles.fields.permissions") }}</th>
                            <th>{{ __("labels.backend.action") }}</th>
                        </tr>
                    </thead>
                    <tbody>
                        @foreach ($roles as $role)
                        <tr>
                            <td>
                                <strong>
                                    {{ $role->name }}
                                </strong>
                            </td>
                            <td>
                                @if($role->name === 'admin')
                                    All Permissions
                                @else
                                @foreach ($role->permissions as $permission)
                                <li>{{ $permission->name }}</li>
                                @endforeach
                                @endif
                            </td>
                            <td>
                                @include('backend.includes.action_column.view-action', ['moduleName' => 'roles', 'item' => $role])
                                @if($role->id !== \App\Models\Role::ADMIN_ROLE)
                                @include('backend.includes.action_column.edit-action', ['moduleName' => 'roles', 'item' => $role])
                                @include('backend.includes.action_column.delete-action', ['moduleName' => 'roles', 'item' => $role])
                                @endif
                            </td>
                        </tr>
                        @endforeach
                    </tbody>
                </table>
        </div>
    </div>
    <div class="card-footer">
        <div class="row">
            <div class="col-7">
                <div class="float-left">
                    {!! $roles->total() !!} {{ __('labels.backend.total') }}
                </div>
            </div>
            <div class="col-5">
                <div class="float-right">
                    {!! $roles->render() !!}
                </div>
            </div>
        </div>
    </div>
</div>

@endsection
